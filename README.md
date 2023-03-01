# android-course

## Правила работы с репозиторием
Процесс работы выстраиваем следующим образом: в этом репозитории для каждого создана ветка (если не создана - скажите мне). Вам необходмо форкнуть этот репозиторий себе (fork) и переключиться на ветку со своим именем. Далее, вся ваша работа должна происходить в уже вашем репозитории, на ветке с вашим именем (как вы работаете с ветками в своем репозитории уже ваше дело - хотите git flow, хотите single branch, что угодно, главное, чтобы релизные изменения лежали на ветке с вашей фамилией). После завершения работ, нужно создать pull-request на исходный репозиторий (этот), запросом на слияние вашей ветки и ветки с вашим именем в этом репозитории. **Огромная просьба - не тянуть до конца семестра, и не заливать весь код кучей в самый последний момент. Я просто не успею все это проверить.**

Было решено, что вы можете выбирать проект. Наиболее активные из вас предложили свои идеи. Предложенные мной проекты выделены курсивом, с ними я смогу помочь лучше и быстрее. Далее идет список проектов, за которые можно взяться:

## Проекты
### **_Проект 1 - Погодное приложение_**
Простое приложение по получению данных о погоде и ее отображению. Один экран, список айтемов на каждый день недели. В этом проекте можно научиться основам, поработать с API.  
*Самый простой проект из списка*  
**`Стек: Kotlin, Android Material, OpenWeather API, DiffUtil(опционально), GSON`**

### **_Проект 2 - Приложение с гуглокартами_**
Идея - приложение для велосипедистов. Необходимо получать положение устройства по GPS, отрисовывать маршрут по точкам, сохранять его, сохранять время прохождения этого маршрута. Подключить firebase для хранения маршрутов на удаленном сервере. Также необходимо реализовать локальное хранилище, на случай, если интернета не будет. Опционально - сделать возможность делиться пройденными маршрутами, устраивать соревнование, кто быстрее пройдет указанный маршрут, сделать списки.  
**`Стек: Kotlin, Android Material, Google Maps API, Firebase, Dagger 2 или Hilt, GSON, Moshi`**

### Проект 3 - Мобильное приложение для сессий настольных Role Play
Приложение работает в двух режимах. Режим для ГМ и режим игрока.
ГМ может выбирать игровые сессии (миры). Каждая сессия содержит сведения о доступных персонажах и сценариях.
ГМ может прочитать сведения о каждом персонаже (но не его инвентарь) и накладывать состояния на игроков.
ГМ может создавать новые сценарии, загружать описание в виде текстовых документов и рисунков, где будет информация о монстрах, секретах, сюжете и т.д.
Игроку доступна вкладка для выбора созданных им персонажем для текущей сессии, выбранной ГМ. После выбора персонажа доступны вкладки со сведениями о персонаже и инвентарем.
Игрок может менять состояние персонажа с помощью предметов. Игрок может экипировать снаряжение для своего персонажа.
Приложение лишь показывает актуальные сведения и позволяет менять состояния (изменение HP и назначение приемуществ игроков и/или проклятий и т.д.).  
**`Стек: Полная свобода. Единственное требование - Kotlin`**

### Проект 4 - Приложение для работы с 3D графикой
Основной функционал, который будет в приложении:
- Сканирование пространства;
- Вывод опорных точек;
- Вывод 3D-модели на экран устройства;
- Перемещение 3D-модели;
- Поверните 3D-модель на 360 градусов;
- Масштабирование 3D-модели;
- Возможность просмотра 3D-модели со всех сторон.  
**`Стек: Полная свобода. Единственное требование - Kotlin`**

Этот файл будет дополняться ссылками на ресурсы с информацией
## Теоретическая информация
### Верстка
**Начало разработки под Android**
+ [Гайды](https://developer.android.com/training/index.html)

**Верстка**
+ [Создание макетов в XML](https://developer.android.com/guide/topics/ui/declaring-layout.html)

**Типы layout'ов**
+ [Frame Layout](http://developer.alexanderklimov.ru/android/layout/framelayout.php)
+ [Linear Layout](https://developer.alexanderklimov.ru/android/layout/linearlayout.php)
+ [Relative Layout](https://developer.alexanderklimov.ru/android/layout/relativelayout.php)

**Constraint Layout**
+ [Документация](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html)
+ [Работа с различными свойствами](https://habrahabr.ru/company/touchinstinct/blog/326814/)

**Списки**
+ [ListView](http://developer.alexanderklimov.ru/android/views/listview.php)
+ [RecyclerView и Adapter](https://developer.android.com/training/material/lists-cards.html)
+ [DiffUtils](https://medium.com/@iammert/using-diffutil-in-android-recyclerview-bdca8e4fbb00)

**Прочее**
+ [Шрифты в XML](https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml.html)
+ [Загружаемые шрифты](https://developer.android.com/guide/topics/ui/look-and-feel/downloadable-fonts.html)
+ [Поддержка разных экранов](https://developer.android.com/guide/practices/screens_support.html)

## V. Android OS. Activity. Fragments
**1. Android OS:** 
+ [История Android](https://www.android.com/history/#/marshmallow) **(\*\*)**
+ [Архитектура Android](https://source.android.com/devices/architecture/) **(\*\*)**

**2. Application:**  
+ [Application](https://developer.android.com/reference/android/app/Application.html)  **(\*\*\*)**
+ [Context](https://possiblemobile.com/2013/06/context/)  **(\*\*\*)**
+ [Файл Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro.html) **(\*\*\*\*)**

**3. Activity:**  
+ [Activity - основы](https://developer.android.com/guide/components/activities.html) **(\*\*\*\*)**
+ [Task и Back Stack](https://habrahabr.ru/post/186434/) **(\*\*)**
+ [Передача данных между Activity](https://developer.android.com/guide/components/activities/parcelables-and-bundles.html) **(\*\*\*\*)**
+ [Управление жизненным циклом Activity](https://developer.android.com/training/basics/activity-lifecycle/index.html) **(\*\*\*\*)**

**4. Fragment:**  
+ [Fragment - основы](https://developer.android.com/guide/components/fragments.html) **(\*\*\*\*)**
+ [Диалоговые окна](https://developer.android.com/guide/topics/ui/dialogs.html) **(\*\*\*\*)**
+ [Обработка изменений конфигурации экрана](https://developer.android.com/guide/topics/resources/runtime-changes.html?hl=ru) **(\*\*\*\*)**
+ [Target fragment](https://habrahabr.ru/post/259805/) **(\*\*)**


**5. Работа со сторонними приложениями и permissions:**  
+ [Run-time permissions](https://developer.android.com/training/permissions/requesting.html)**(\*\*\*\*)**
+ [Intent и фильтры](https://developer.android.com/guide/components/intents-filters.html?hl=ru)**(\*\*\*\*)**
+ [Взаимодействие с другими приложениями](https://developer.android.com/training/basics/intents/index.html )**(\*\*\*)**

**6. BroadcastReceiver:**  
+ [BroadcastReceiver - основы](http://codetheory.in/android-broadcast-receivers/) **(\*\*\*\*)**
+ [Изменения работы с BroadcastReceiver с Android 8.0](https://developer.android.com/guide/components/broadcast-exceptions.html) **(\*\*)**


