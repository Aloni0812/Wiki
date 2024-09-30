# Требования к проекту
## 1. Введение
В эпоху цифровой информации создание и поддержка платформы для управления знаниями становятся все более актуальными. Проект под названием "Wiki" предназначен для создания веб-приложения, позволяющего пользователям легко управлять и делиться информацией.
Wiki предоставляет пользователям возможность создавать, редактировать и просматривать статьи, обеспечивая удобный и интуитивно понятный интерфейс. Приложение будет служить надежным инструментом для коллективного ввода и хранения данных.
## 2. Требования пользователя
## 2.1 Программные интерфейсы
1. Java: Основной язык программирования для разработки приложения.
2. Spring Boot/Maven: Фреймворк и инструмент для упрощения конфигурации и сборки проекта.
3. PostgreSQL: Реляционная база данных для хранения информации.
4. Swagger: Инструмент для документирования и тестирования API.
## 2.2 Интерфейс пользователя
Действие пользователя	Реакция системы
Ввод данных для создания нового вики-запроса
Редактирование запроса, возможность удаления запроса.  
## 2.3 Характеристики пользователей
1.	Школьники
•	Возраст: 10-17
•	Образование: Нет
•	Опыт: Средний уровень пользование интернетом, желание найти информацию 
•	Техническая грамотность: Умение использовать смартфоном
___
2.	Студенты 
•	Возраст: 17-21  
•	Образование: Среднее
•	Опыт: Профиссионалы списывания, интерес найти информацию 
•	Техническая грамотность: Умение использовать смартфоном на отличном уровне 
__
3.	Остальные пользватели 
•	Возраст: 25-100 лет
•	Образование: Любое
•	Опыт: Любой
## 2.4 Предположения и зависимости
1.	Изменения в технологиях:
•	Появление новых инструментов и фреймворков для веб разработки.
2.	Потребности пользователей:
•	Обратная связь от пользователей, выявляющая новые функциональные требования.
3.	Конкуренция:
•	Появление аналогичных сайтов с расширенными функциями.
4.	Законодательство и безопасность:
•	Обновления в законах о защите данных и безопасности пользователей.
5.	Изменения в бизнес-целях:
•	Корректировка общей стратегии компании, влияющая на функциональность приложения.
6.	Аппаратные ограничения:
•	Ограничения производительности и памяти на устройствах пользователей.
## 3 Системные требования
## 3.1 Функциональные требования
1.	Авторизация и регистрация пользователей
•	Пользователи должны иметь возможность зарегистрироваться или войти в свой аккаунт
•	При регистрации необходимо заполнить поля для логина, имени и пароля
•	После успешной авторизации пользователь должен получить доступ к профилю
2.	Профиль пользователя
•	Отображение информации о пользователе (ник)
•	История добавления исправления статей
•	Возможность добавить новую статью
3.	Справочник вики запросов
•	Список популярных запросов с описанием 
•	Возможность редактирования выбранного статьи 
4.	Страница отчёта об ошибке
   Вывод информации об ошибке
•	Возвращение на главную страницу
5.	Страница добавления статьи 
•	Поле ввода названия статьи
•	Поле ввода описание статьи
•	Кнопка подтверждения
## 3.2 Нефункциональные требования
1.	Авторизация и регистрация пользователей
o	Пользователи должны иметь возможность зарегистрироваться или войти в свой аккаунт
o	При регистрации необходимо заполнить поля для логина, имени и пароля
o	После успешной авторизации пользователь должен получить доступ к профилю
2.	Профиль пользователя
o	Отображение информации о пользователе (Ник)
o	История редактирования статей
3.	Справочник вики запросов 
o	Список всех вики запросов
o	Возможность просмотра статьи
4.	Страница отчёта об ошибке
o	Вывод ошибок 
5.	Добовление статьи 
o	Поле описания статьи
o	Поле статьи 
## 3.2 Нефункциональные требования
## 3.2.1 Атрибуты качества
### 1.	Надежность
•	Приложение должно работать стабильно и без сбоев
•	Все функции должны быть доступны во всех режимах работы устройства
•	Обработка ошибок должна быть корректной и информативной
### 2.	Безопасность
•	Защита пользовательских данных от несанкционированного доступа
•	Шифрование чувствительных данных (пароли, личная информация)
•	Проверка входных данных на наличие вредоносных кодов
### 3.	Производительность
•	Приложение должно быстро открываться и отвечать на действия пользователя
•	Операции с данными (сохранение, чтение) должны выполняться быстро
•	Отображение информации должно происходить плавно без задержек
### 4.	Интерактивность
•	Интуитивно понятный и удобный интерфейс
•	Простота навигации между различными разделами сайта
•	Отзывчивый дизайн, реагирующий на взаимодействие пользователя
### 5.	Масштабируемость
•	Приложение должно легко масштабироваться при росте числа пользователей
•	Возможность добавления новых функций без существенного влияния на производительность
### 6.	Тестирование
•	Наличие полного набора тестовых случаев для каждого функционала
•	Регулярное проведение автоматических и ручных тестов
•	Мониторинг производительности и выявление потенциальных проблем
### 7.	Доступность
•	Приложение должно быть доступно на русском языке
•	Наличие опций для пользователей с ограниченными возможностями

