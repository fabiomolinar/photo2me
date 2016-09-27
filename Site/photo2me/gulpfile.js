var
  gulp = require('gulp'),
  semanticBuild = require('semantic-ui/tasks/build'),
  semanticWatch = require('semantic-ui/tasks/watch');

gulp.task('jquery',function(){
  return gulp.src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('public/js'));
});
gulp.task('semanticBuild',semanticBuild);
gulp.task('semanticWatch',semanticWatch);
