# RTPhonecode
Cервис подсказки телефонных кодов.

## Краткое описание
Сервис помогает пользователю узнать телефонный код страны, задавая всего пару букв в
названии страны. Например:
GET /rest/code?country=be

 [
    
    {
        "name": "BE",
        "country": "Belgium",
        "code": "32"
    },
    
    {
        "name": "BJ",
        "country": "Benin",
        "code": "229"
    },
    
    {
        "name": "BM",
        "country": "Bermuda",
        "code": "+1-441"
    },
    
    {
        "name": "BY",
        "country": "Belarus",
        "code": "375"
    },
    
    {
        "name": "BZ",
        "country": "Belize",
        "code": "501"
    }
    
]



Данные сервис берет из источников и приводит их к указанному выше формату:
- Старны: http://country.io/names.json
- Телефоныне коды: http://country.io/phone.json

К сожалению, возникают критичные ситуации, когда источники могут быть недоступны. Для того, чтобы пользователи не чувствовали неудобств от таких ситуаций, в сервисе используется кэширование. Также, на случай падения сервиса, предусмотрено кэширование в файловую систему, откуда можно получить кэш вручную в виде файла.
Сервис позволяет обновлять кэш в любое время. Для этого необходимо отправить gзапрос GET /rest/code/upload 


## Обрабатываемые запросы
  
  - GET /rest/code?country= "Часть названия страны" 
      Возвращает записи, которые начинаются с подстроки country.Регистр подстроки не играет роли.   

      Cледующие запросы возвращают один и тот же результат
      GET /rest/code?country=be
      GET /rest/code?country=Be
      GET /rest/code?country=bE
      
  - GET /rest/code/upload 
      Обновляет существующий кэш. В случае отсутствия кэша - создает его.
    
## Логирование
  Логирование осуществляется в консоль и в файл App.log. Файл сохраняется в директорию, содержающую выполняемый проект.
  
## Кэширование
  Для увеличения производительности и отказоустойчивости программы использется технология кэширования.
  В кэш помещаются данные о странах из http://country.io/names.json и данные о телефонных кодах из http://country.io/phone.json. При возникновении критичных ситуаций предусмотрено сохранение кэша в памяти. Кэш находится по адресу  {java.io.tmpdir}/jsonhelpercache.data

## Требования 
  - git
  - maven
  - docker
  
## Установка и запуск

  1)Клонирование репозитория 
    git clone https://github.com/maqfromspace/RTPhonecode.git
    
  2)Перейти в директорию, где находится склонированный проект
  
  3)Сборка проекта
    mvn install
  
  4)Создание docker image
    docker build -t rtservice .
  
  5)docker run -p 8080:8080 rtservice
