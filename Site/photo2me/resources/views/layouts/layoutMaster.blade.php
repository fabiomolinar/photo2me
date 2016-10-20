<!DOCTYPE html>
<!DOCTYPE html>
<html>
  <head>
    {{-- Meta --}}
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="author" content="Fabio Thomaz Molinar">
    <meta name="contact" content="fabiomolinar@gmail.com">
    <meta name="keywords" content="{{ trans('keywords.head') }}">
    {{-- CSS --}}
    <link rel="stylesheet" type="text/css" href="{{ asset('/css/main.min.css') }}">
    {{-- JS --}}
    <script type="text/javascript" src="{{ asset('/js/jquery.min.js') }}"></script>
    <script type="text/javascript" src="{{ asset('/js/semantic.min.js') }}"></script>
    <script type="text/javascript" src="{{ asset('/js/main.js') }}"></script>
    @include('elements.globais')
    {{-- Trackers --}}
    <?php // TODO: Adicionar trackers! ?>
    @yield('head')
  </head>
  <body>
    @yield('sideHeader')
    <div id="master-content" class="pusher">
      <div id="header">
        @yield('header')
      </div>
      <div id="conteudo">
        @yield('conteudo')
      </div>
      <div id="footer">
        <script type="text/javascript">
          $(document).ready(function(){
            _FtmMaster.init();
          });
        </script>
        @yield('footer')
      </div>
    </div>
    @include('elements.modals.modals')
  </body>
</html>
