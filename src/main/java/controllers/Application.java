package controllers;


import platform.followed_users.application.follow.*;
import platform.followed_users.application.search.*;
import platform.followed_users.domain.FollowedUsersRepository;
import platform.followed_users.infraestructure.MySqlFollowedUsersRepository;
import platform.interests.application.SearchAllInterestsQuery;
import platform.interests.application.SearchAllInterestsQueryHandler;
import platform.interests.application.InterestSearcher;
import platform.interests.domain.InterestRepository;
import platform.interests.infrastructure.MySqlInterestRepository;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.shared.infrastructure.MemoryCommandBus;
import platform.shared.infrastructure.MemoryQueryBus;
import platform.shared.infrastructure.MySqlConnection;
import platform.shared.infrastructure.MySqlCriteriaAdapter;
import platform.tweet.application.create.CreateTweetCommand;
import platform.tweet.application.create.CreateTweetCommandHandler;
import platform.tweet.application.create.TweetCreator;
import platform.tweet.application.delete.DeleteTweetCommand;
import platform.tweet.application.delete.DeleteTweetCommandHandler;
import platform.tweet.application.delete.TweetDeleter;
import platform.tweet.application.find.FindTweetByIdQuery;
import platform.tweet.application.find.FindTweetByIdQueryHandler;
import platform.tweet.application.find.TweetFinder;
import platform.tweet.application.like.LikeTweetCommand;
import platform.tweet.application.like.LikeTweetCommandHandler;
import platform.tweet.application.like.TweetLiker;
import platform.tweet.application.reply.TweetReplier;
import platform.tweet.application.search.*;
import platform.tweet.application.update.TweetUpdater;
import platform.tweet.application.update.UpdateTweetCommand;
import platform.tweet.application.update.UpdateTweetCommandHandler;
import platform.tweet.domain.TweetRepository;
import platform.tweet.infrastructure.MySqlTweetRepository;
import platform.user_interests.application.create.UserInterestCreator;
import platform.user_interests.application.search.SearchUserInterestsQuery;
import platform.user_interests.application.search.SearchUserInterestsQueryHandler;
import platform.user_interests.application.search.UserInterestSearcher;
import platform.user_interests.domain.UserInterestRepository;
import platform.user_interests.infrastructure.MySqlUserInterestRepository;
import platform.user_pictures.application.find.FindUserPictureQuery;
import platform.user_pictures.application.find.FindUserPictureQueryHandler;
import platform.user_pictures.application.find.UserPictureFinder;
import platform.user_pictures.domain.UserPictureRepository;
import platform.user_pictures.infrastructure.GravatarUserPictureRepository;
import platform.users.application.delete.DeleteUserCommand;
import platform.users.application.delete.DeleteUserCommandHandler;
import platform.users.application.delete.UserDeleter;
import platform.users.application.find.FindUserByEmailQuery;
import platform.users.application.find.FindUserByEmailQueryHandler;
import platform.users.application.find.UserFinder;
import platform.users.application.login.LoginUserCommand;
import platform.users.application.login.LoginUserCommandHandler;
import platform.users.application.login.UserLogin;
import platform.users.application.find.*;
import platform.users.application.register.RegisterUserCommand;
import platform.users.application.register.RegisterUserCommandHandler;
import platform.users.application.register.UserRegister;
import platform.users.application.search.SearchUsersWithSimilarInterestsQuery;
import platform.users.application.search.SearchUsersWithSimilarInterestsQueryHandler;
import platform.users.application.search.UserSearcher;
import platform.users.application.update.UpdateUserCommand;
import platform.users.application.update.UpdateUserCommandHandler;
import platform.users.application.update.UserUpdater;
import platform.users.domain.UserRepository;
import platform.users.infrastructure.MySqlUserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Application implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Repositories
        MySqlConnection connection;
        try {
            connection = new MySqlConnection();
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
        MySqlCriteriaAdapter criteriaAdapter = new MySqlCriteriaAdapter();
        UserRepository userRepository = new MySqlUserRepository(connection, criteriaAdapter);
        UserInterestRepository userInterestRepository = new MySqlUserInterestRepository(connection, criteriaAdapter);
        InterestRepository interestRepository = new MySqlInterestRepository(connection);
        TweetRepository tweetRepository = new MySqlTweetRepository(connection, criteriaAdapter);
        UserPictureRepository userPictureRepository = new GravatarUserPictureRepository();
        FollowedUsersRepository followedUsersRepository = new MySqlFollowedUsersRepository(connection, criteriaAdapter);

        // Buses
        CommandBus commandBus = new MemoryCommandBus();
        QueryBus queryBus = new MemoryQueryBus();

        // Services
        UserFinder userFinder = new UserFinder(userRepository);
        UserInterestCreator userInterestCreator = new UserInterestCreator(userInterestRepository);
        UserInterestSearcher userInterestSearcher = new UserInterestSearcher(userInterestRepository);
        InterestSearcher interestSearcher = new InterestSearcher(interestRepository);
        UserRegister userRegister = new UserRegister(interestSearcher, userRepository, userInterestCreator, queryBus);
        TweetReplier tweetReplier = new TweetReplier(tweetRepository);
        TweetCreator tweetCreator = new TweetCreator(tweetRepository, queryBus, tweetReplier);
        TweetSearcher tweetSearcher = new TweetSearcher(tweetRepository, queryBus);
        UserLogin userLogin = new UserLogin(userRepository);
        FollowedUsersSearcher followedUsersSearcher = new FollowedUsersSearcher(followedUsersRepository);
        UserPictureFinder userPictureFinder = new UserPictureFinder(userPictureRepository);
        UserFollower userFollower = new UserFollower(followedUsersRepository, queryBus);
        TweetFinder tweetFinder = new TweetFinder(tweetRepository);
        TweetDeleter tweetDeleter = new TweetDeleter(tweetRepository, tweetFinder, queryBus, tweetReplier);
        TweetUpdater tweetUpdater = new TweetUpdater(tweetRepository, queryBus);
        TweetLiker tweetLiker = new TweetLiker(tweetRepository);
        UserSearcher userSearcher = new UserSearcher(userRepository);
        UserUpdater userUpdater = new UserUpdater(userRepository);
        UserDeleter userDeleter = new UserDeleter(userRepository);

        // Commands
        commandBus.register(RegisterUserCommand.class, new RegisterUserCommandHandler(userRegister));
        commandBus.register(LoginUserCommand.class, new LoginUserCommandHandler(userLogin));
        commandBus.register(CreateTweetCommand.class, new CreateTweetCommandHandler(tweetCreator));
        commandBus.register(FollowUserCommand.class, new FollowUserCommandHandler(userFollower));
        commandBus.register(UnFollowUserCommand.class, new UnFollowUserCommandHandler(userFollower));
        commandBus.register(DeleteTweetCommand.class, new DeleteTweetCommandHandler(tweetDeleter));
        commandBus.register(UpdateTweetCommand.class, new UpdateTweetCommandHandler(tweetUpdater));
        commandBus.register(UpdateUserCommand.class, new UpdateUserCommandHandler(userUpdater));
        commandBus.register(DeleteUserCommand.class, new DeleteUserCommandHandler(userDeleter));
        commandBus.register(LikeTweetCommand.class, new LikeTweetCommandHandler(tweetLiker));

        // Queries
        queryBus.register(FindUserByEmailQuery.class, new FindUserByEmailQueryHandler(userFinder));
        queryBus.register(FindUserByIdQuery.class, new FindUserByIdQueryHandler(userFinder));
        queryBus.register(SearchFollowedUsersQuery.class, new SearchFollowedUsersQueryHandler(followedUsersSearcher));
        queryBus.register(SearchAllInterestsQuery.class, new SearchAllInterestsQueryHandler(interestSearcher));
        queryBus.register(SearchTrendingTweetsQuery.class, new SearchTrendingTweetsQueryHandler(tweetSearcher));
        queryBus.register(SearchTrendingTweetsOfContactsQuery.class, new SearchTrendingTweetsOfContactsQueryHandler(tweetSearcher));
        queryBus.register(FindUserPictureQuery.class, new FindUserPictureQueryHandler(userPictureFinder));
        queryBus.register(SearchFollowedUserQuery.class, new SearchFollowedUserQueryHandler(followedUsersSearcher));
        queryBus.register(SearchUsersWithSimilarInterestsQuery.class, new SearchUsersWithSimilarInterestsQueryHandler(userSearcher));
        queryBus.register(SearchUserInterestsQuery.class, new SearchUserInterestsQueryHandler(userInterestSearcher));
        queryBus.register(SearchUserTweetsQuery.class, new SearchUserTweetsQueryHandler(tweetSearcher));
        queryBus.register(FindTweetByIdQuery.class, new FindTweetByIdQueryHandler(tweetFinder));
        queryBus.register(SearchTweetsRepliesQuery.class, new SearchTweetsRepliesQueryHandler(tweetSearcher));

        // Controllers
        ServletContext context = servletContextEvent.getServletContext();
        context.addServlet("register", new RegisterController(commandBus, queryBus)).addMapping("/register");
        context.addServlet("login", new LoginController(commandBus, queryBus)).addMapping("/login");
        context.addServlet("home", new HomeController(commandBus, queryBus)).addMapping("/home");
        context.addServlet("logout", new LogoutController(commandBus, queryBus)).addMapping("/logout");
        context.addServlet("profile", new ProfileController(commandBus, queryBus)).addMapping("/profile");
        context.addServlet("explore", new ExploreController(commandBus, queryBus)).addMapping("/explore");
        context.addServlet("followed", new FollowedController(commandBus, queryBus)).addMapping("/followed");
        context.addServlet("like", new LikeController(commandBus, queryBus)).addMapping("/like");
        context.addServlet("replies", new RepliesController(commandBus, queryBus)).addMapping("/replies");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
