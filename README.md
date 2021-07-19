<h1 align="center">
  <img alt="RSSpaper logo" src="resources/static/img/icons/default-alpha.png">
</h1>
<p align="center">
  <a href="https://rsspaper.andros.dev/">👉 DEMO 👈</a> (My own feed generated daily with Github Actions)
</p>

## Run

1) Create a file `config.yaml` with the following content. You can also use `config.yaml.example` as a base config and change it to fit your needs.

``` yaml
title: RSSPaper

# Options: light or dark
theme: light

# Options: daily, weekly or all
edition: weekly

feeds:
  - https://programadorwebvalencia.com/feed/
  - https://republicaweb.es/feed/
```

2) Download the latest version of RSSpaper (`rsspaper-{version}-standalone.jar`).

https://github.com/tanrax/RSSpaper/releases


3) Now you can execute.

```sh
java -jar rsspaper-{version}-standalone.jar
```

Great 🎉. You already have your 📰 own Static RSS Newspaper 📰.

That's it, now you just have to open `dist/index.html`.
