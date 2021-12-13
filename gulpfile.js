const gulp = require('gulp');
const postcss = require('gulp-postcss');
const cleancss = require('gulp-clean-css');
const tailwind = require('tailwindcss')

gulp.task('build', () =>
    gulp.src(['src/main/resources/main.css'])
        .pipe(postcss([tailwind]))
        .pipe(cleancss())
        .pipe(gulp.dest('src/main/webapp/vendor'))
);