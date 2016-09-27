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
    <link rel="stylesheet" type="text/css" href="{{ asset('/css/master.css') }}">
    {{-- JS --}}
    <script type="text/javascript" src="{{ asset('/js/jquery-1.12.3.js') }}"></script>
    {{-- Trackers --}}
    <?php // TODO: Adicionar trackers! ?>
    @yield('head')
  </head>
  <body>
    <div id="super-wrapper">
      <div class="container-fluid master-wrapper">
        <div id="header">
          @yield('header')
        </div>
        <div id="conteudo">
          @yield('conteudo')
        </div>
        <div id="footer">
          @yield('footer')
          @include('modal.modalMensagem')
        </div>
      </div>
    </div>
  </body>
</html>
