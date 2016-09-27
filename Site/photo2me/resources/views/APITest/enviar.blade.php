<link rel="stylesheet" type="text/css" href="{{ asset('/css/semantic.min.css') }}">
{{-- Enviar foto para o servidor --}}
<form class="" action="receberFoto" method="post" enctype="multipart/form-data">
  <input type="hidden" name="MAX_FILE_SIZE" value="30000">
  Select image to upload:
  <input type="file" name="imagem" id="imagem">
  <br>
  Apelido:
  <input type="text" name="apelido" value="">
  idUsuarioFesta
  <input type="text" name="idUsuarioFesta" value="">
  <br>
  lastModified
  <input type="text" name="lastModified" value="">
  <input type="submit" value="Upload Image" name="submit">
</form>
Testando o semantic:
<button class="ui primary basic button">Primary</button>
<label>First Name</label>
<input type="text" name="first-name" placeholder="First Name">
