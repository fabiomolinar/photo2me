var
  gulp = require('gulp'),
  plugins = require('gulp-load-plugins')({
    DEBUG: false
  });
plugins.requireDir = require('require-dir'),
plugins.semantic = plugins.requireDir('resources/assets/semantic/tasks'),
plugins.runSequence = require('run-sequence'),
plugins.gulpCSSNano = require('gulp-cssnano'),
plugins.gulpConcat = require('gulp-concat'),
plugins.gulpClean = require('gulp-clean'),
plugins.gulpUglify = require('gulp-uglify');

//Pegando o jquery da pasta node_modules e enviando para a pasta public
gulp.task('jquery',function(){
  return gulp.src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('public/js'));
});
//Tarefas do semantic dentro de resources
gulp.task('semanticBuild',plugins.semantic.build);
gulp.task('semanticWatch',plugins.semantic.watch);
//Transferindo os arquivos compilados do semantic para a pasta public
gulp.task('semanticJS',function(){
  return gulp.src('resources/assets/semantic/dist/semantic.min.js')
    .pipe(gulp.dest('public/js'));
});
gulp.task('semanticCSS',function(){
  return gulp.src('resources/assets/semantic/dist/semantic.min.css')
    .pipe(gulp.dest('public/css'));
});
gulp.task('semanticThemes',function(){
  return gulp.src('resources/assets/semantic/dist/themes/**/*')
    .pipe(gulp.dest('public/themes'))
});
//Agrupar todos os JSs da pasta public/js
gulp.task('agruparJS',function(){
  return gulp.src(['public/js/**/*.js','!public/js/jquery.min.js','!public/js/semantic.min.js'])
    .pipe(plugins.gulpUglify())
    .pipe(plugins.gulpConcat('main.min.js'))
    .pipe(gulp.dest('public/js'));
});
gulp.task('agruparJSTeste',function(){
  return gulp.src(['public/js/**/*.js','!public/js/jquery.min.js','!public/js/semantic.min.js'])
    .pipe(plugins.gulpConcat('main.js'))
    .pipe(gulp.dest('public/js'));
});
//Agrupar todos os CSSs da pasta public/css
gulp.task('agruparCSS',function(){
  return gulp.src('public/css/**/*.css')
    .pipe(plugins.gulpCSSNano())
    .pipe(plugins.gulpConcat('main.min.css'))
    .pipe(gulp.dest('public/css'));
});
//Deletar os main.min.(js|css)
gulp.task('deletarMainMin',function(){
  return gulp.src('public/**/main*.+(css|js)')
    .pipe(plugins.gulpClean());
});

//WATCHES
//Rodar os watches
gulp.task('watch',function(){
  //Watch dos específicos
  gulp.watch(['public/**/*.+(css|js)','!public/**/main*.+(css|js)'],['buildTeste']);
  //Main watch
  gulp.watch(['semantic.json','resources/assets/semantic/**/*'],['build']);
});

//BUILDERS
//Main build
gulp.task('build',function(callback){
  plugins.runSequence(
    ['jquery','semanticBuild'],
    ['semanticJS','semanticCSS','semanticThemes'],
    ['deletarMainMin'],
    ['agruparJS','agruparCSS'],
    callback
  );
});
//Build específico
gulp.task('buildEspecifico',function(callback){
  plugins.runSequence(
    ['deletarMainMin'],
    ['agruparJS','agruparCSS'],
    callback
  );
});
gulp.task('buildTeste',function(callback){
  plugins.runSequence(
    ['deletarMainMin'],
    ['agruparJSTeste','agruparCSS'],
    callback
  );
});

//Default
gulp.task('default',['build']);
