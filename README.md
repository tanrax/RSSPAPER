<h1 align="center">
  <img alt="RSSpaper logo" src="media/newsreader-banner.png">
</h1>

## Demo

<p align="center">
  <a href="https://rsspaper.andros.dev/">👉 My own feed generated daily with Github Actions 👈</a>
</p>

### PageSpeed Insights

![demo preview](media/pagespeed-insights.png)

## Screenshots

![demo preview](media/demo.jpg)

Generate a static page with the latest news from your favorite feeds. Is it an RSS client? Yes, except that it generates an **HTML/PWA** that you can read or **install on your tablet or mobile** and to **update the news you must run it again**.

## Run

1) Create a file `config.yaml` with the following content. You can also use `config.yaml.example` as a base config and change it to fit your needs.

``` yaml
# Change the title, it's for you. Maybe you see "My newspaper"?
title: RSSPAPER

# Options: light or dark
theme: light

# Options: daily, weekly or all
edition: weekly

feeds:
  - https://programadorwebvalencia.com/feed/
  - https://republicaweb.es/feed/
```

2) Download the latest version of RSSpaper (`rsspaper-{version}-standalone.jar`).

https://github.com/tanrax/RSSPAPER/releases

(Both `jar` and `config.yaml` must be in the same directory)

3) Now you can execute.

```sh
java -jar rsspaper-{version}-standalone.jar
```

Great 🎉. You already have your 📰 own Static RSS Newspaper 📰.

That's it, now you just have to open `dist/index.html`.

## Donation target

- **50+ euros**: Dark Theme.
- **100+ euros**: RSS feed generation to be read by other clients.
- **150+ euros**: Integrated web server and auto-compilation.
- **200+ euros**: Documentation for creating new templates and custom themes.

<p align="center">
  <a href='https://ko-fi.com/androsfenollosa' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://cdn.ko-fi.com/cdn/kofi2.png?v=2' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>
</p>
