module.exports = {
    theme: {},
    variants: {
        extend: {
            backgroundColor: ["disabled"],
            textColor: ["disabled"]
        },
    },
    plugins: [
        require('@tailwindcss/forms')({
            strategy: 'class',
        })
    ],
}
