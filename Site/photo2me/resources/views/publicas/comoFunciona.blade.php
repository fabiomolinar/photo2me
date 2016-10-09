@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.comoFunciona') }}">
  <title>{{ trans('titles.comoFunciona') }}</title>
  <meta name="Description" content="{{ trans('descriptions.comoFunciona') }}">
@endsection

@section('sideHeader')
  @include('elements.headers.sideHeader')
@endsection

@section('header')
  @include('elements.headers.header')
@endsection

@section('conteudo')
  <div class="ui segment">
    <div class="ui container">
      <div class="ui grid">
        
      </div>
    </div>
  </div>
@endsection

@section('footer')
  @include('elements.footers.footer')
  {{-- JS --}}
  <script type="text/javascript">
    $(document).ready(function(){
      $(window).resize(function(){
      });
    });
  </script>
@endsection
