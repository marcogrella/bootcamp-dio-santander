package com.project.bootcamp.util;

/* Quando ocorrer um erro não é o ideal colocar a mensagem do erro direto no construtor
*  desta. O ideal é centralizar as mensages para que quando forem alteradas seja alterado
* em um único lugar. Isso porque durante as inserções no banco de dados utilizamos a classe
* BusinessException para gerenciar as exceções e as mensagem recebidas por essa classe devem vir
* deste ponto.
* Essa classe é abstrata, então não queremos que instanciem objetos desta.
*
* */

public abstract class MessageUtils {

    public static final String STOCK_ALREADY_EXISTS = "Stock already exists in the database";

    public static final String NO_RECORDS_FOUND = "No records found in database.";
}
