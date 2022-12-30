### Легенда

Не всегда удобно "таскать" за собой полноценный Tomcat (скачивать, распаковывать его и т.д.). Достаточно часто используют библиотеку [WebApp Runner](https://github.com/heroku/webapp-runner), ранее (com.github.jsimone webapp-runner).

Встраивание webapp-runner'а в ваш проект позволяет запускать его (проект) следующим образом: `java -jar target/dependency/webapp-runner.jar target/<appname>.war` (достаточно удобно для размещения на облачных платформах).

### Задача

Добавьте в свою сборку скачивание `webapp-runner`'а согласно [инструкции](https://github.com/heroku/webapp-runner#using-with-maven-in-your-project).

Убедитесь, что сборка проходит и ваш war-файл действительно запускается указанной выше командой.

### Результат

Реализуйте новую функциональность в ветке `feature/webapp-runner` вашего репозитория из предыдущего ДЗ и откройте Pull Request.

В качестве результата пришлите ссылку на ваш Pull Request на GitHub в личном кабинете студента на сайте [netology.ru](https://netology.ru).

После того, как ДЗ будет принято, сделайте `merge` для Pull Request'а.