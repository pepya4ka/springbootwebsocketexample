package com.pepyachka.myspringbootwebsocketexample.model;

public enum TypeRequest {

    CONNECT,//Подключение игрока к лобби
    DISCONNECT,//Отключение игрока от лобби
    CREATE,//Создание лобби
    DELETE,//Удаление лобби
    UPDATE,//Обновление готовности игрока
    START//Начало игры

}
