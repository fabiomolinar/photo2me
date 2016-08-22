{{-- Enviar foto para o servidor --}}
<form class="" action="receberFoto" method="post" enctype="multipart/form-data">
  <input type="hidden" name="MAX_FILE_SIZE" value="30000">
  Select image to upload:
  <input type="file" name="imagem" id="imagem">
  <br>
  Apelido:
  <input type="text" name="apelido" value="">
  <input type="submit" value="Upload Image" name="submit">
</form>
