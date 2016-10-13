@extends('layouts.layoutPrivadas')

@section('headPrivadas')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.detalhesEvento') }}">
  <title>{{ trans('titles.detalhesEvento') }}</title>
  <meta name="Description" content="{{ trans('descriptions.detalhesEvento') }}">
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