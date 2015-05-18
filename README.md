# Play Swagger module
Playframework 1.3 module to integrate swagger in your application.

## Add it to your project
Put the dependency in your `dependencies.yml` file:
```lang
require:
    - boblakk -> swagger 0.0.1
```

Define the repository to get the module from github:
```lang
repositories:
   - boblakk-play-github:
       type:       http
       artifact:   "https://github.com/[organization]/play-[module]/raw/master/release/[module]-[revision].zip"
       contains:
           - boblakk -> *
```

## Add routes to your application
In your `conf/routs` file of your application put something like this:
```
# import these routes in the main app like this:
*     /api                    module:swagger
```
You can change the path as you want.

## Configure the module
Create the file `swagger.yml` in your applications `conf` directory:
```lang
info:
    descrption: "Example API description"
    version: "1.0"
    title: "Example API"
    termsOfService: "http://example.com/terms"
    contact:
        name: "Contact Name"
        url: "http://example.com/contact"
        email: "contact@example.com"
    license:
        name: "Version title"
        url: "http://example.com/license"
### If the next value is present, the host is static and not resolved by the request
host: "example.com"
basePath: "/api"
```
Values passed here are used to provide information about your service in the generated `swagger.json`

## Version table
Play Swagger module version     | Swagger core version | Play version
------------------------------- | -------------------- | ------------
0.0.1                           | 1.5.2-M1             | 1.3.1
