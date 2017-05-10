## angular-colorpicker
![angular-colorpicker](https://github.com/linjinying/angular-colorpicker/blob/master/screenshot.png)  

###注意
该颜色选择器将作为ui组件的形式并入[angular-components](https://github.com/linjinying/angular-components/tree/master/angular-colorpicker)。

###Annoucement
a new initiative, more active is available at [angular-components](https://github.com/linjinying/angular-components/tree/master/angular-colorpicker).

####Demo
[click here](http://w3cin.com/demo/angular-components/angular-colorpicker/)


一个基于angularJS的颜色选择器，目前具备以下特性：
- **3种选色模式**  经典模式、自定义选择模式、历史记录
- **支持的颜色格式**  目前仅支持rgb,rgba,hex，默认是hex格式
- **支持语言设置**  默认是中文

a beautiful color picker based on angularJS.It supports the following features:
- **3 modes of picker**  pallete, custom, history
- **Color types**  rgb, rgba ,hex.
- **Languages**  zh-cn, zh-tw, en, pt.

####Requirements
- **jquery**
- **angular 1.3.x**（one-time binding）

####Install
> bower install angular-colorpicker

> npm install angular-colorpicker

####Basic Usage
```html
<colorpicker color="color" color-type="rgba" color-language="zh-CN"></colorpicker>
var app = angular.module('app',['ui.colorpicker']);
```

#### Options
- **color**  颜色值
- **color-type** 颜色格式(rgb,rgba,hex) Default `hex`
- **color-language** 语言设置(zh-cn,zh-tw,en...) Default `zh-cn`


#### Browser Support
`ie9+`  `chrome` `firefox` `safari`

####License
--------
This colopicker plugin is licensed under the MIT license.

Copyright (c) 2016 linjinying
