# gitbook 使用说明

## 安装

```
npm install gitbook-cli -g
```

## 目录结构

```
> doc/
	|-> README.md 
	|-> SUMMARY.md   	//文档目录文件
	|-> GLOSSARY.md  	//默认词汇表文件
	|-> book.json   	//gitbook配置文件，包含插件引用
	|-> _book/  	    //编译之后的html文件目录
	|-> node_modules/	//node package安装目录
	|-> .gitignore		//忽略文件
```

## 操作

```
gitbook init
```

> 该命令会自动生成README.md，SUMMARY.md，GLOSSARY.md

```
gitbook install
```

> 运行该命令会将book.json中依赖的插件自动安装

```
gitbook build
```

> 运行该命令后会在书籍的文件夹中生成一个 `_book` 文件夹, 里面的内容即为生成的 html 文件. 我们可以使用该命令来生成网页而不开启服务器 

```
gitbook serve
```

>  使用该命令会运行一个服务器, 通过`http://localhost:4000/`可以预览 

## 命令

```
gitbook init //初始化目录文件
gitbook help //列出gitbook所有的命令
gitbook --help //输出gitbook-cli的帮助信息
gitbook build //生成静态网页
gitbook serve //生成静态网页并运行服务器
gitbook build --gitbook=2.0.1 //生成时指定gitbook的版本, 本地没有会先下载
gitbook ls //列出本地所有的gitbook版本
gitbook ls-remote //列出远程可用的gitbook版本
gitbook fetch 标签/版本号 //安装对应的gitbook版本
gitbook update //更新到gitbook的最新版本
gitbook uninstall 2.0.1 //卸载对应的gitbook版本
gitbook build --log=debug //指定log的级别
gitbook builid --debug //输出错误信息
```

```
$ gitbook help 

build [book] [output]      build a book
    --format      Format to build to (Default is website; Values are website, json, ebook)
    --log      Minimum log level to display (Default is info; Values are debug, info, warn, error, disabled)

  pdf [book] [output]      build a book to pdf
    --log      Minimum log level to display (Default is info; Values are debug, info, warn, error, disabled)

  epub [book] [output]      build a book to epub
    --log      Minimum log level to display (Default is info; Values are debug, info, warn, error, disabled)

  mobi [book] [output]      build a book to mobi
    --log      Minimum log level to display (Default is info; Values are debug, info, warn, error, disabled)

  serve [book]      Build then serve a gitbook from a directory
    --port      Port for server to listen on (Default is 4000)
    --lrport      Port for livereload server to listen on (Default is 35729)
    --watch      Enable/disable file watcher (Default is true)
    --format      Format to build to (Default is website; Values are website, json, ebook)
    --log      Minimum log level to display (Default is info; Values are debug, info, warn, error, disabled)

  install [book]      install plugins dependencies

  init [directory]      create files and folders based on contents of SUMMARY.md
```

