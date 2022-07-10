# courseJavaSchool
Данный проект выполнен по тз курсовой работы<br />
Используемый фреймворк spring, весь код написан на java<br />
Автор использовал в качестве базы данных mysql, возможно использование другой базы данных<br />
Данный проект представляет из себя API в который входит 1 контроллер с 3мя методами <br />
### Запуск
Для запуска необходимо изменить параметры файла с подключением к базе данных "application.properties"<br />
### Документация
Наименование контроллера _AdvertisementController_, путь к данному контроллеру .../advertisement<br /><br />
Добавление не просмотренной рекламы пользователем осуществляется методом "setViewed"<br />
обращение к методу POST .../advertisement/setViewed<br />
отправляемая структура методу:<br />
```
{
  "viewed" : [ {
    "userGuid" : "ec77a962-fd38-11ec-b84f-b42e99986ec1",
    "contentGuid" : "041ffa27-fd39-11ec-b84f-b42e99986ec1"
  }, {
    "userGuid" : "ec0adca6-fd38-11ec-b84f-b42e99986ec1",
    "contentGuid" : "03ae4812-fd39-11ec-b84f-b42e99986ec1"
  } ]
}
```
Ответ от сервера - ResponseEntity
<br />

Получение информации по странице за сегодняшний день осуществляется методом POST .../advertisement/getTargeting<br />
page является параметром метода,является строкой и наименованием получаемой страницы<br />
Возвращаемая структура PageTargetModel<br />
Структура PageTargetModel<br />
Поля PageTargetModel:<br />
String page - наименование страницы<br />
String startDate - начало периода<br />
String endDate - окончание периода<br />
List<TargetModel> target - список пользователей и контента<br />
Стркутура TargetModel<br />
Поля TargetModel<br />
String userGuid - идентификатор пользователя<br />
List<OffersModel> offers<br />
Структура OffersModel<br />
Поля OffersModel<br />
String contentGuid - идентификатор контента<br />
byte priority - приоритет контента, выбирается случайно<br />
Пример возвращаемой структуры:<br />
```
{
   "page":"test1",
   "startDate":"07072022",
   "endDate":"08072022",
   "target":[
      {
         "userGuid":"ec77a962-fd38-11ec-b84f-b42e99986ec1",
         "offers":[
            {
               "contentGuid":"041ffa27-fd39-11ec-b84f-b42e99986ec1",
               "priority":31
            },
            {
               "contentGuid":"041ffa27-fd39-11ec-b84f-b42e99986ec1",
               "priority":91
            },
            {
               "contentGuid":"041ffa27-fd39-11ec-b84f-b42e99986ec1",
               "priority":3
            }
         ]
      }
   }
}
```
Добавление информации о контенте предназначенный для каждой страницы<br />
Обращение к методу осуществляется POST /advertisement/setPublishedContent<br />
отправляемая структура методу:<br />
```
{
  "content" : [ {
    "pages" : [ {
      "pageName" : "test1"
    }, {
      "pageName" : "test2"
    } ],
    "contentGuid" : "041ffa27-fd39-11ec-b84f-b42e99986ec1"
  } ]
}
```
Ответ от сервера - ResponseEntity
<br />





