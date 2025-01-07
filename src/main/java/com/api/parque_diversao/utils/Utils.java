package com.api.parque_diversao.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class Utils {

	/**
     * Formata um número de telefone com o padrão do país.
     *
     * @param numero Número de telefone
     * @param pais Código do país (e.g. "BR")
     * @return Número de telefone formatado ou exceção se o número for inválido
     */
    public static String formatarTelefone(String numero, String pais) {

        if(numero.length() != 11) {
            throw new IllegalArgumentException("Número de telefone inválido");
        }

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {

            PhoneNumber phoneNumber = phoneUtil.parse(numero, pais);
            return phoneUtil.format(phoneNumber, PhoneNumberFormat.NATIONAL);

        } catch (NumberParseException e) {
            // Tratar exceção caso o número seja inválido
            throw new IllegalArgumentException("Número de telefone inválido");
        }

    }

    /**
     * Formata um CPF no padrão brasileiro.
     *
     * @param cpf CPF não formatado, composto por 11 dígitos.
     * @return CPF formatado 
     * @throws StringIndexOutOfBoundsException se o CPF não tiver 11 dígitos.
     */
    public static String formatarCpf(String cpf) {

        if(cpf.length() < 11) {
            throw new StringIndexOutOfBoundsException("CPF inválido");
        }

        return String.format("%s.%s.%s-%s",
                cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9));
    }

    /**
     * Verifica e retorna a placa do veículo se estiver em um formato válido.
     *
     * Este método aceita dois formatos de placas de veículos:
     * - Formato antigo: "AAA-1234", onde A são letras e 1 são dígitos.
     * - Formato Mercosul: "AAA1B23", onde A são letras, 1 é um dígito e B é uma letra.
     *
     * @param placa A placa do veículo a ser verificada.
     * @return A placa do veículo se estiver em um dos formatos válidos.
     * @throws IllegalArgumentException se a placa não corresponder a nenhum dos formatos esperados.
     */
    public static String formatarPlaca(String placa) {

        // Padrão para placas antigas (AAA-1234)
        String regexAntigo = "[A-Z]{3}-\\d{4}";
        // Padrão para placas Mercosul (AAA1B23)
        String regexNovo = "[A-Z]{3}\\d[A-Z]\\d{2}";

        Pattern patternAntigo = Pattern.compile(regexAntigo);
        Pattern patternNovo = Pattern.compile(regexNovo);

        Matcher matcherAntigo = patternAntigo.matcher(placa);
        Matcher matcherNovo = patternNovo.matcher(placa);

        if (matcherAntigo.matches()) {
            return placa; // Já está no formato antigo
        } else if (matcherNovo.matches()) {
            return placa; // Já está no formato novo
        } else {
            // Se não corresponder a nenhum padrão, retornar a placa original
            throw new IllegalArgumentException("Placa inválido");
        }
    }

	
}
