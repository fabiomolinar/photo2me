@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="robots" content="none" />
  @yield('headPrivadas')
@endsection

@section('sideHeader')
  @include('elements.headers.sideHeader')
@endsection

@section('header')
  @include('elements.headers.header')
  @yield('headerPrivadas')
@endsection

@section('conteudo')
  @yield('conteudoPrivadas')
@endsection

@section('footer')
  <script type="text/javascript">
    $(document).ready(function(){
        _FtmPrivadas.init();
      $(window).resize(function(){
        _FtmPrivadas.resize();
      })
    });
  </script>  
  @yield('footerPrivadas')
@endsection
