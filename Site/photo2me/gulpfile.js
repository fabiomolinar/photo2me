var
  gulp = require('gulp'),
  requireDir = require('require-dir'),
  semantic = requireDir('resources/assets/semantic/tasks');

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
    .pipe(gulp.dest('public/js'))
});
gulp.task('semanticCSS',function(){
  return gulp.src('resources/assets/semantic/dist/semantic.min.css')
    .pipe(gulp.dest('public/css'))
});
