var
  gulp = require('gulp'),
  requireDir = require('require-dir'),
  semantic = requireDir('resources/assets/semantic/tasks'),
  runSequence = require('run-sequence'),
  gulpCSSNano = require('gulp-cssnano'),
  gulpConcat = require('gulp-concat'),
  gulpClean = require('gulp-clean'),
  gulpUglify = require('gulp-uglify');

//Pegando o jquery da pasta node_modules e enviando para a pasta public
gulp.task('jquery',function(){
  return gulp.src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('public/js'));
});
//Tarefas do semantic dentro de resources
gulp.task('semanticBuild',semantic.build);
gulp.task('semanticWatch',semantic.watch);
//Transferindo os arquivos compilados do semantic para a pasta public
gulp.task('semanticJS',function(){
  return gulp.src('resources/assets/semantic/dist/semantic.min.js')
    .pipe(gulp.dest('public/js'));
});
gulp.task('semanticCSS',function(){
  return gulp.src('resources/assets/semantic/dist/semantic.min.css')
    .pipe(gulp.dest('public/css'));
});
//Agrupar todos os JSs da pasta public/js
gulp.task('agruparJS',function(){
  return gulp.src('public/js/**/*.js')
    .pipe(gulpUglify())
    .pipe(gulpConcat('main.min.js'))
    .pipe(gulp.dest('public/js'));
});
//Deletar os main.min.(js|css)
gulp.task('deletarMainMin',function(){
  return gulp.src('public/**/main.min.+(css|js)')
    .pipe(gulpClean());
});
//Agrupar todos os CSSs da pasta public/css
gulp.task('agruparCSS',function(){
  return gulp.src('public/css/**/*.css')
    .pipe(gulpCSSNano())
    .pipe(gulpConcat('main.min.css'))
    .pipe(gulp.dest('public/css'));
});
//Main build
gulp.task('build',function(callback){
  runSequence(
    ['jquery','semanticBuild'],
    ['semanticJS','semanticCSS'],
    ['deletarMainMin'],
    ['agruparJS','agruparCSS']
  );
});

//Default
gulp.task('default',['build']);
