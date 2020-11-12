package com.pepyachka.myspringbootwebsocketexample.model;

public enum TypeStatus {

    SUCCESS,//сообщение отправлено и получено на обработку без ошибок и проблем
    ACCESS_DENIED,//доступ к команде запрещён
    INVALID_DATA,//присваивается, если данные были отправлены, но введены неверно
    INVALID_REQUEST,//неправильно отправленный JSON-запрос
    UNKNOWN_ERROR//статус присваивается, если ни один статус к сообщению невозможно присвоить

}
