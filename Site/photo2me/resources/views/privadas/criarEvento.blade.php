@extends('layouts.layoutPrivadas')

@section('headPrivadas')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.criarEvento') }}">
  <title>{{ trans('titles.criarEvento') }}</title>
  <meta name="Description" content="{{ trans('descriptions.criarEvento') }}">
@endsection

@section('headerPrivadas')
  @include('elements.headers.dashboard')
@endsection

@section('conteudoPrivadas')

@endsection

@section('footerPrivadas')
  @include('elements.footers.dashboard')
  {{-- JS --}}
  <script type="text/javascript">
    $(document).ready(function(){
      $(window).resize(function(){
      });
    });
  </script>
@endsection