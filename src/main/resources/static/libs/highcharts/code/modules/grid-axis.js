/*
 Highcharts JS v7.1.0 (2019-04-01)

 GridAxis

 (c) 2016-2019 Lars A. V. Cabrera

 License: www.highcharts.com/license
*/
(function(e){"object"===typeof module&&module.exports?(e["default"]=e,module.exports=e):"function"===typeof define&&define.amd?define("highcharts/modules/grid-axis",["highcharts"],function(p){e(p);e.Highcharts=p;return e}):e("undefined"!==typeof Highcharts?Highcharts:void 0)})(function(e){function p(g,e,p,k){g.hasOwnProperty(e)||(g[e]=k.apply(null,p))}e=e?e._modules:{};p(e,"parts-gantt/GridAxis.js",[e["parts/Globals.js"]],function(g){var e=g.addEvent,p=g.dateFormat,k=g.defined,w=g.isArray,x=g.isNumber,
q=function(a){return g.isObject(a,!0)},y=g.merge,u=g.pick,z=g.wrap,f=g.Axis,B=g.Tick,v={top:0,right:1,bottom:2,left:3,0:"top",1:"right",2:"bottom",3:"left"};f.prototype.isNavigatorAxis=function(){return/highcharts-navigator-[xy]axis/.test(this.options.className)};f.prototype.isOuterAxis=function(){var a=this,b=-1,c=!0;a.chart.axes.forEach(function(d,h){d.side!==a.side||d.isNavigatorAxis()||(d===a?b=h:0<=b&&h>b&&(c=!1))});return c};f.prototype.getMaxLabelDimensions=function(a,b){var c={width:0,height:0};
b.forEach(function(b){b=a[b];var d;q(b)&&(d=q(b.label)?b.label:{},b=d.getBBox?d.getBBox().height:0,d=x(d.textPxLength)?d.textPxLength:0,c.height=Math.max(b,c.height),c.width=Math.max(d,c.width))});return c};g.dateFormats.W=function(a){a=new Date(a);var b;a.setHours(0,0,0,0);a.setDate(a.getDate()-(a.getDay()||7));b=new Date(a.getFullYear(),0,1);return Math.ceil(((a-b)/864E5+1)/7)};g.dateFormats.E=function(a){return p("%a",a,!0).charAt(0)};e(B,"afterGetLabelPosition",function(a){var b=this.label,c=
this.axis,d=c.reversed,h=c.chart,l=c.options,e=l&&q(l.grid)?l.grid:{},l=c.options.labels,t=l.align,n=v[c.side],m=a.tickmarkOffset,r=c.tickPositions,g=this.pos-m,r=x(r[a.index+1])?r[a.index+1]-m:c.max+m,f=c.tickSize("tick",!0),m=w(f)?f[0]:0,f=f&&f[1]/2,k;!0===e.enabled&&("top"===n?(e=c.top+c.offset,k=e-m):"bottom"===n?(k=h.chartHeight-c.bottom+c.offset,e=k+m):(e=c.top+c.len-c.translate(d?r:g),k=c.top+c.len-c.translate(d?g:r)),"right"===n?(n=h.chartWidth-c.right+c.offset,d=n+m):"left"===n?(d=c.left+
c.offset,n=d-m):(n=Math.round(c.left+c.translate(d?r:g))-f,d=Math.round(c.left+c.translate(d?g:r))-f),this.slotWidth=d-n,a.pos.x="left"===t?n:"right"===t?d:n+(d-n)/2,a.pos.y=k+(e-k)/2,h=h.renderer.fontMetrics(l.style.fontSize,b.element),b=b.getBBox().height,l.useHTML?a.pos.y+=h.b+-(b/2):(b=Math.round(b/h.h),a.pos.y+=(h.b-(h.h-h.f))/2+-((b-1)*h.h/2)),a.pos.x+=c.horiz&&l.x||0)});e(f,"afterTickSize",function(a){var b=this.maxLabelDimensions,c=this.options;!0===(c&&q(c.grid)?c.grid:{}).enabled&&(c=2*
Math.abs(this.defaultLeftAxisOptions.labels.x),b=c+(this.horiz?b.height:b.width),w(a.tickSize)?a.tickSize[0]=b:a.tickSize=[b])});e(f,"afterGetTitlePosition",function(a){var b=this.options;if(!0===(b&&q(b.grid)?b.grid:{}).enabled){var c=this.axisTitle,d=c&&c.getBBox().width,h=this.horiz,e=this.left,g=this.top,t=this.width,n=this.height,m=b.title,b=this.opposite,r=this.offset,f=this.tickSize()||[0],k=m.x||0,p=m.y||0,A=u(m.margin,h?5:10),c=this.chart.renderer.fontMetrics(m.style&&m.style.fontSize,c).f,
f=(h?g+n:e)+f[0]/2*(b?-1:1)*(h?1:-1)+(this.side===v.bottom?c:0);a.titlePosition.x=h?e-d/2-A+k:f+(b?t:0)+r+k;a.titlePosition.y=h?f-(b?n:0)+(b?c:-c)/2+r+p:g-A+p}});z(f.prototype,"unsquish",function(a){var b=this.options;return!0===(b&&q(b.grid)?b.grid:{}).enabled&&this.categories?this.tickInterval:a.apply(this,Array.prototype.slice.call(arguments,1))});e(f,"afterSetOptions",function(a){var b=this.options;a=a.userOptions;var c,d=b&&q(b.grid)?b.grid:{};!0===d.enabled&&(c=y(!0,{className:"highcharts-grid-axis "+
(a.className||""),dateTimeLabelFormats:{hour:{list:["%H:%M","%H"]},day:{list:["%A, %e. %B","%a, %e. %b","%E"]},week:{list:["Week %W","W%W"]},month:{list:["%B","%b","%o"]}},grid:{borderWidth:1},labels:{padding:2,style:{fontSize:"13px"}},margin:0,title:{text:null,reserveSpace:!1,rotation:0},units:[["millisecond",[1,10,100]],["second",[1,10]],["minute",[1,5,15]],["hour",[1,6]],["day",[1]],["week",[1]],["month",[1]],["year",null]]},a),"xAxis"===this.coll&&(k(a.linkedTo)&&!k(a.tickPixelInterval)&&(c.tickPixelInterval=
350),k(a.tickPixelInterval)||!k(a.linkedTo)||k(a.tickPositioner)||k(a.tickInterval)||(c.tickPositioner=function(a,b){var d=this.linkedParent&&this.linkedParent.tickPositions&&this.linkedParent.tickPositions.info;if(d){var e,h,m,f,l=c.units;for(f=0;f<l.length;f++)if(l[f][0]===d.unitName){e=f;break}if(l[e][1])return l[e+1]&&(m=l[e+1][0],h=(l[e+1][1]||[1])[0]),d=g.timeUnits[m],this.tickInterval=d*h,this.getTimeTicks({unitRange:d,count:h,unitName:m},a,b,this.options.startOfWeek)}})),y(!0,this.options,
c),this.horiz&&(b.minPadding=u(a.minPadding,0),b.maxPadding=u(a.maxPadding,0)),x(b.grid.borderWidth)&&(b.tickWidth=b.lineWidth=d.borderWidth))});e(f,"afterSetAxisTranslation",function(){var a=this.options,b=a&&q(a.grid)?a.grid:{},c=this.tickPositions&&this.tickPositions.info,d=this.userOptions.labels||{};this.horiz&&(!0===b.enabled&&this.series.forEach(function(a){a.options.pointRange=0}),c&&(!1===a.dateTimeLabelFormats[c.unitName].range||1<c.count)&&!k(d.align)&&(a.labels.align="left",k(d.x)||(a.labels.x=
3)))});e(f,"trimTicks",function(){var a=this.options,b=a&&q(a.grid)?a.grid:{},c=this.categories,d=this.tickPositions,e=d[0],f=d[d.length-1],g=this.linkedParent&&this.linkedParent.min||this.min,k=this.linkedParent&&this.linkedParent.max||this.max,n=this.tickInterval;!0!==b.enabled||c||!this.horiz&&!this.isLinked||((e>g||e<g&&e+n>g)&&!a.startOnTick&&(d[0]=g),(f<k||f>k&&f-n<k)&&!a.endOnTick&&(d[d.length-1]=k))});e(f,"afterRender",function(){var a=this.options,b=a&&q(a.grid)?a.grid:{},c,d,e,f,g,k,n=this.chart.renderer,
m=this.horiz;if(!0===b.enabled&&(b=2*Math.abs(this.defaultLeftAxisOptions.labels.x),this.maxLabelDimensions=this.getMaxLabelDimensions(this.ticks,this.tickPositions),b=this.maxLabelDimensions.width+b,c=a.lineWidth,this.rightWall&&this.rightWall.destroy(),d=this.axisGroup.getBBox(),this.isOuterAxis()&&this.axisLine&&(m&&(b=d.height-1),c))){d=this.getLinePath(c);g=d.indexOf("M")+1;k=d.indexOf("L")+1;e=d.indexOf("M")+2;f=d.indexOf("L")+2;if(this.side===v.top||this.side===v.left)b=-b;m?(d[e]+=b,d[f]+=
b):(d[g]+=b,d[k]+=b);this.axisLineExtra?this.axisLineExtra.animate({d:d}):this.axisLineExtra=n.path(d).attr({stroke:a.lineColor,"stroke-width":c,zIndex:7}).addClass("highcharts-axis-line").add(this.axisGroup);this.axisLine[this.showAxis?"show":"hide"](!0)}});e(f,"init",function(a){function b(){var a=c.options,b=25/11,d=c.chart.renderer.fontMetrics(a.labels.style.fontSize);a.labels||(a.labels={});a.labels.align=u(a.labels.align,"center");c.categories||(a.showLastLabel=!1);c.horiz&&(a.tickLength=h.cellHeight||
d.h*b);c.labelRotation=0;a.labels.rotation=0}var c=this,d=c.chart,h=(a=a.userOptions)&&q(a.grid)?a.grid:{},l,p,t;if(h.enabled)if(k(h.borderColor)&&(a.tickColor=a.lineColor=h.borderColor),w(h.columns)){p=0;for(t=h.columns.length;t--;)l=y(a,h.columns[t],{type:"category"}),delete l.grid.columns,l=new f(c.chart,l),l.isColumn=!0,l.columnIndex=p,z(l,"labelFormatter",function(a){var c=this.axis,b=c.tickPositions,d=this.value,e=d===b[0],b=d===b[b.length-1],f=g.find(c.series[0].options.data,function(a){return a[c.isXAxis?
"x":"y"]===d});this.isFirst=e;this.isLast=b;this.point=f;return a.call(this)}),p++;e(this,"afterInit",function(){g.erase(d.axes,this);g.erase(d[c.coll],this)})}else e(this,"afterInit",b)})});p(e,"masters/modules/grid-axis.src.js",[],function(){})});
//# sourceMappingURL=grid-axis.js.map
