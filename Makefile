.DEFAULT_GOAL := build

build:
	make build.templates
	lein uberjar
	make rm.statics
	echo "Finish!"

build.templates:
	make rm.statics
	tar cf resources/themes/light/static.tar --directory=./resources/themes/light/ static
	tar cf resources/themes/dark/static.tar --directory=./resources/themes/dark/ static
	tar cf resources/themes/sepia/static.tar --directory=./resources/themes/sepia/ static
	tar cf resources/themes/clojure/static.tar --directory=./resources/themes/clojure/ static

rm.statics:
	rm -rf resources/themes/dark/static.tar
	rm -rf resources/themes/sepia/static.tar
	rm -rf resources/themes/light/static.tar
	rm -rf resources/themes/clojure/static.tar
