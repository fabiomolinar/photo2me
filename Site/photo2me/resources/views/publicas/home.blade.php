@extends('layouts.layoutMaster')

@section('head')
  {{-- Meta --}}
  <meta name="keywords" content="{{ trans('keywords.home') }}">
  <title>{{ trans('titles.home') }}</title>
  <meta name="Description" content="{{ trans('descriptions.home') }}">
@endsection

@section('sideHeader')
  @include('elements.headers.sideHeader')
@endsection

@section('header')
  @include('elements.headers.header')
@endsection

@section('conteudo')
{{-- Banner --}}
  <div class="ui attached center aligned segment" id="baner-segment">
    <div class="ui container">
      <div class="ui centered grid">
        <div class="row">
          <div class="thirteen wide center aligned column">
            <h1 class="ui header">{{ trans('messages.moto') }}</h1>
            <h3 class="ui header">{{ trans('messages.chamada-longa') }}</h3>
          </div>
        </div>
        <div class="row">
          <div class="six wide center aligned column">
            <button class="fluid ui large button" type="button" name="button">{{ trans('messages.como-funciona') }}</button>
          </div>
          <div class="six wide center aligned column">
            <button class="fluid ui large button" type="button" name="button">{{ trans('messages.comecar-agora') }}</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  {{-- Vantagens --}}
  <div class="ui bottom attached segment">
    <div class="ui container">
      <div class="ui centered stackable grid">
        @foreach ($vantagens as $vantagem)
        <div class="four wide center aligned column">
          @include('elements.cards.vantagem')
        </div>
        @endforeach
      </div>
    </div>
  </div>
  {{-- Explicação mais longa --}}
  <div class="ui bottom attached segment">
    <div class="ui container">
      <div class="ui centered grid">
        <div class="five wide middle aligned column">
          <img class="ui fluid middle aligned image" src="img/logo.png" alt="" />
        </div>
        <div class="seven wide justified column">
          <h2 class="ui header">{{ trans('messages.moto') }}</h2>
          <p class="">{{ trans('messages.explicacao-longa') }}</p>
          <button class="ui fluid button" type="button" name="button">{{ trans('messages.descubra-mais') }}</button>
        </div>
      </div>
    </div>
  </div>
@endsection

@section('footer')
  @include('elements.footers.footer')
  {{-- JS --}}
  <script type="text/javascript">
    $(document).ready(function(){
      _FtmHome.init();
      $(window).resize(function(){
        _FtmHome.resize();
      });
    });
  </script>
@endsection
