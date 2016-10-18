<?php

namespace App\Http\Requests;

use App\Http\Requests\Request;

use Lang;

class ContatoRequest extends Request
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'nome' => 'required',
            'email' => 'required|email'
        ];
    }

    public function messages()
    {
        return [
            'nome.required' => Lang::get('form.fvNomeObrigatorio'),
            'email.email' => Lang::get('messages.fvEmailFormato'),
            'email.required' => Lang::get('form.fvEmailObrigatorio')
        ];
    }
}
