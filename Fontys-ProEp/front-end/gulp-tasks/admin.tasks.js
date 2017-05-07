
// TODO: Fix all-in-one JS, etc. For now just make sure we understand the structure.
// TODO: Put the general task (bower) and (shared components) in here since it should be executed only once.

var gulp = require('gulp');
var connect = require('gulp-connect');
var jshint = require('gulp-jshint');
var uglify = require('gulp-uglify');
var minifyCSS = require('gulp-minify-css');
var clean = require('gulp-clean');
var runSequence = require('run-sequence');
var clear = require('clear');

var publicURL = './public/app-admin';

var public = {
    'styles': publicURL + '/css',
    'scripts': publicURL + '/js'
};

var cleanPaths = [
    public.styles + '/*',
    public.scripts + '/*'
];

var cors = function (req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Headers', '*');
    next();
};

/**
 * Tasks
 */

gulp.task('lint-admin', function () {
    gulp.src(['./app-admin/**/*.js', '!./bower_components/**'])
        .pipe(jshint())
        .pipe(jshint.reporter('default'))
        .pipe(jshint.reporter('fail'));
});

gulp.task('clean-admin', function () {
    clear();
    return gulp
        .src(cleanPaths, {read: false})
        .pipe(clean());
});

gulp.task('minify-css-admin', function () {
    var opts = {comments: true, spare: true};
    gulp.src(['./app-admin/**/*.css', '!./bower_components/**'])
        .pipe(minifyCSS(opts))
        .pipe(gulp.dest('./public/app-admin/'))
});

gulp.task('minify-js-admin', function () {
    gulp.src(['./app-admin/**/*.js', '!./bower_components/**'])
        .pipe(gulp.dest('./public/app-admin/'))
});

gulp.task('copy-bower-components-admin', function () {
    gulp.src('./bower_components/**')
        .pipe(gulp.dest('public/bower_components/'));
});

gulp.task('copy-html-files-admin', function () {
    gulp.src('./app-admin/**/*.html')
        .pipe(gulp.dest('./public/app-admin/'));
});

gulp.task('copy-components-admin', function () {
    gulp.src('./components/**')
        .pipe(gulp.dest('public/components/'));
});

gulp.task('copy-services-admin', function () {
    gulp.src('./services/**')
        .pipe(gulp.dest('public/services/'));
});

gulp.task('connect-admin', function () {
    connect.server({
        root: 'app-admin/',
        port: 8881,
        middleware: function(connect) {
            return [
                connect().use('/bower_components', connect.static('bower_components')),
                connect().use('/components', connect.static('components')),
                connect().use('/services', connect.static('services')),
                connect().use('/asset', connect.static('asset'))
            ];
        }
    });
});

gulp.task('connect-public-admin', function () {
    connect.server({
        root: 'public/app-admin/',
        port: 9991,
        middleware: function(connect) {
            return [
                connect().use('/bower_components', connect.static('bower_components')),
                connect().use('/components', connect.static('components')),
                connect().use('/services', connect.static('services')),
                connect().use('/asset', connect.static('asset'))
            ];
        }
    });
});

/**
 * Run sequence tasks
 */

gulp.task('build-admin', function () {
    runSequence(
        ['clean-admin', 'lint-admin', 'minify-css-admin', 'minify-js-admin', 'copy-html-files-admin',
            'copy-bower-components-admin', 'copy-components-admin', 'copy-services-admin', 'connect-public-admin']
    );
});