/*
 * jQuery placeholder, fix for IE6,7,8,9
 * @author JENA
 * @since 20131115.1504
 * @website ishere.cn
 */
//var JPlaceHolder = {
//    //检测
//    _check : function(){
//        return 'placeholder' in document.createElement('input');
//    },
//    //初始化
//    init : function(){
//        if(!this._check()){
//            this.fix();
//        }
//    },
//    //修复
//    fix : function(){
//        jQuery(':input[placeholder]').each(function(index, element) {
//            //var self = $(this), txt = self.attr('placeholder');
//            //self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));
//            //var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
//           // var holder = $('<span></span>').text(txt).css({position:'absolute', left:pos.left, top:pos.top, height:h, line-height:h, paddingLeft:paddingleft, color:'#aaa'}).appendTo(self.parent());
//           //	console.log(txt);
////            if(self.val()){
////            	console.log(txt);
////           		 self.val(txt);
////           	}
////            self.focusin(function(e) {
////            	if(self.val()){
////                   self.val("");
////                }else{
////                	self.val(txt);
////                }
////                //holder.hide();
////            }).focusout(function(e) {
////                if(self.val()){
////                   self.val(txt);
////                }
////            });
//            //holder.click(function(e) {
//               // holder.hide();
//              //  self.focus();
//            //});
//        	placeholder();
//        });
//    }
//};

//function placeholder(nodes,pcolor) {
//      if(nodes.length && !("placeholder" in document_createElement_x("input"))){
//              var self = nodes[i], placeholder = self.getAttribute('placeholder') || '';     
//              self.onfocus = function(){
//                  if(self.value == placeholder){
//                     self.value = '';
//                     self.style.color = "";
//                  }               
//              }
//              self.onblur = function(){
//                  if(self.value == ''){
//                      self.value = placeholder;
//                      self.style.color = pcolor;
//                  }              
//              }                                       
//              self.value = placeholder;  
//              self.style.color = pcolor;              
//     }
//}

//执行
window.onload =function(){
  //判断浏览器是否支持placeholder属性
  supportPlaceholder='placeholder'in document.createElement('input'),
  placeholder=function(input){
    var text = input.attr('placeholder'),
//  defaultValue = input.defaultValue?input.defaultValue:input.value;
    defaultValue = (text!=null&&text!="")?true:false;
//  alert((input.defaultValue!=null||input.defaultValue!="")+"  1 "+text!=null+" 12 "+text);
//    alert((text!=null&&text!="")+"  "+  text);
    if(defaultValue){
      input.val(text).addClass("phcolor");
    }
    input.focus(function(){
      if(input.val() == text){
        $(this).val(""); 
      }
    });
    input.blur(function(){
      if(input.val() == ""){
        $(this).val(text).addClass("phcolor");
      }
    });
    //输入的字符不为灰色
    input.keydown(function(){
      $(this).removeClass("phcolor");
    });
  };
 
  //当浏览器不支持placeholder属性时，调用placeholder函数
  if(!supportPlaceholder){
    //.attr('placeholder')
	  $('input').each(function(){
      text = $(this).attr("placeholder");
      if($(this).attr("type") == "text"){
        placeholder($(this));
      }
    });
  }
}

//$(function(){  
// 
//  //判断浏览器是否支持placeholder属性
//  supportPlaceholder='placeholder'in document.createElement('input'),
// 
//  placeholder=function(input){
// 
//    var text = input.attr('placeholder'),
//    defaultValue = input.defaultValue;
// 
//    if(!defaultValue){
// 
//      input.val(text).addClass("phcolor");
//    }
// 
//    input.focus(function(){
// 
//      if(input.val() == text){
//   
//        $(this).val("");
//      }
//    });
// 
//  
//    input.blur(function(){
// 
//      if(input.val() == ""){
//       
//        $(this).val(text).addClass("phcolor");
//      }
//    });
// 
//    //输入的字符不为灰色
//    input.keydown(function(){
//  
//      $(this).removeClass("phcolor");
//    });
//  };
// 
//  //当浏览器不支持placeholder属性时，调用placeholder函数
//  if(!supportPlaceholder){
// 
//    $('input').each(function(){
// 
//      text = $(this).attr("placeholder");
// 
//      if($(this).attr("type") == "text"){
// 
//        placeholder($(this));
//      }
//    });
//  }
// 
//});