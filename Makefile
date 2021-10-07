.DEFAULT_GOAL := build

build: ## Check style with black
	make build.templates
	lein uberjar
	make rm.statics

build.templates:
	make rm.statics
	zip -r resources/themes/dark/static.zip resources/themes/dark/static/
	zip -r resources/themes/sepia/static.zip resources/themes/sepia/static/
	zip -r resources/themes/light/static.zip resources/themes/light/static/

rm.statics:
	rm -rf resources/themes/dark/static.zip
	rm -rf resources/themes/sepia/static.zip
	rm -rf resources/themes/light/static.zip
