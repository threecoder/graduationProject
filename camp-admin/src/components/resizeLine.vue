<!--
 * @Author: hitpang
 * @Date: 2020-01-03 13:44:13
 * @LastEditTime : 2020-01-05 22:45:31
 * @LastEditors  : Billy Chen
 * @Description: 可调节相邻容器大小的分割线
 -->
<template>
    <div :id="resizeLineId" class="resize-line">
        <div class="tip">
            <div class="line"></div>
            <div class="line"></div>
            <div class="line"></div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            resizeLineId: "resizeLine-" + Math.round(Math.random() * 10000)
        };
    },
    props: {
        // 左组件/上组件ref的ID
        firstComponent: {
            type: String
        },
        // 右组件/下组件ref的ID
        secondComponent: {
            type: String
        },
        // 调整模式(true:水平拖拉调整页面,false:垂直拖拉调整页面)
        isHorizontal: {
            type: Boolean,
            default: true
        },
        // 左组件/上组件的最小宽度/高度，必须>=0
        firstMinValue: {
            type: Number,
            default: 0
        },
        // 左组件/上组件的最大宽度/高度，必须>=0
        firstMaxValue: {
            type: Number,
            default: 0
        },
        // 右组件/下组件的最大宽度/高度，必须>=0
        secondMinValue: {
            type: Number,
            default: 300
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init() {
            let resizeLine = document.getElementById(this.resizeLineId);
            let firstComponent = this.firstComponent
                ? document.getElementById(this.firstComponent)
                : resizeLine.previousSibling;
            let secondComponent = this.secondComponent
                ? document.getElementById(this.secondComponent)
                : resizeLine.nextSibling;

            if (this.isHorizontal) {
                this.$_addClass(resizeLine, "resize-horizontal");
            } else {
                this.$_addClass(resizeLine, "resize-vertical");
            }

            let firstMinValue = this.firstMinValue; // 左侧最小宽度
            let firstMaxValue = this.firstMaxValue; // 左侧最大宽度
            let secondMinValue = this.secondMinValue; // 右侧最小宽度
            let isHorizontal = this.isHorizontal;
            let startValue, old1stComponentSize, old2ndComponentSize;

            // 当按下鼠标
            resizeLine.onmousedown = e => {
                e = e || window.event;
                // 开始坐标值
                startValue = isHorizontal ? e.clientX : e.clientY;
                // 原来的宽度
                old1stComponentSize =
                    firstComponent[
                        isHorizontal ? "offsetWidth" : "offsetHeight"
                    ];
                old2ndComponentSize =
                    secondComponent[
                        isHorizontal ? "offsetWidth" : "offsetHeight"
                    ];

                // 当鼠标移动
                document.onmousemove = e => {
                    // 移动的时候的坐标值
                    let endValue = isHorizontal ? e.clientX : e.clientY;

                    // 最新的第1个组件的宽度
                    let new1stComponentSize =
                        old1stComponentSize + (endValue - startValue);

                    // 如果第1个组件本来就刚好等于最小值，并且鼠标移动方向还在尝试缩小第1个组件，则直接返回即可
                    if (
                        old1stComponentSize === firstMinValue &&
                        new1stComponentSize < firstMinValue
                    ) {
                        return;
                    }

                    // 最新的第2个组件的宽度
                    let new2ndComponentSize =
                        old2ndComponentSize + (startValue - endValue);

                    // 如果第2个组件本来就刚好等于最小值，并且鼠标移动方向还在尝试缩小第2个组件，则直接返回即可
                    if (
                        old2ndComponentSize === secondMinValue &&
                        new2ndComponentSize < secondMinValue
                    ) {
                        return;
                    }

                    if (new1stComponentSize <= 0) {
                        // console.log("第1个组件的宽度:", new1stComponentSize);
                    }

                    if (new2ndComponentSize <= 0) {
                        // console.log("第2个组件的宽度:", new2ndComponentSize);
                    }

                    if (new1stComponentSize < firstMinValue) {
                        // 左侧过小
                        new1stComponentSize = firstMinValue;
                        // 推算出右侧
                        new2ndComponentSize =
                            old2ndComponentSize +
                            (old1stComponentSize - new1stComponentSize);
                    } else if (new2ndComponentSize < secondMinValue) {
                        // 右侧过小
                        new2ndComponentSize = secondMinValue;
                        // 推算出左侧
                        new1stComponentSize =
                            old1stComponentSize +
                            (old2ndComponentSize - new2ndComponentSize);
                    }
                    if (new1stComponentSize > firstMaxValue) {
                        new1stComponentSize = firstMaxValue;
                        new2ndComponentSize =
                            old2ndComponentSize +
                            (old1stComponentSize - new1stComponentSize);
                    }
                    if (new1stComponentSize >= 6) {
                        new1stComponentSize -= 6;
                    } else {
                        new2ndComponentSize -= 6;
                    }
                    firstComponent.style[isHorizontal ? "width" : "height"] =
                        new1stComponentSize + "px";
                    secondComponent.style[isHorizontal ? "width" : "height"] =
                        new2ndComponentSize + "px";
                };
                // 当松开鼠标
                document.onmouseup = () => {
                    // 置空事件
                    document.onmousemove = null;
                    document.onmouseup = null;
                    // 释放全局捕获
                    resizeLine.releaseCapture && resizeLine.releaseCapture();
                };
                // IE取消默认行为,设置全局捕获
                resizeLine.setCapture && resizeLine.setCapture();
                return false;
            };

            // 当鼠标移上去
            resizeLine.onmouseover = e => {
                this.$_addClass(resizeLine, "resize-hover");
            };
            // 当鼠标移走
            resizeLine.onmouseout = e => {
                this.$_removeClass(resizeLine, "resize-hover");
            };
        },

        $_addClass(dom, cls) {
            if (dom.className.indexOf(cls) < 0) {
                dom.className = dom.className + " " + cls;
            }
        },
        $_removeClass(dom, cls) {
            dom.className = dom.className.replace(cls, "");
        }
    }
};
</script>
<style lang="scss" scoped>
// 分割线的粗细
$thickness: 6px;
// $resize-background-color: rgba(251, 153, 102, 0.5); //（深支子）
$resize-background-color: rgba(0, 225, 235, 0.4);

// $resize-hover-color: rgb(251, 153, 102); //（深支子）
$resize-hover-color: #00e1eb;
$resize-tip-line-color: rgb(17, 50, 133); // （紺青）
.resize-line {
    float: left;
    // display: inline-block;
    // background-color: $resize-background-color;
    box-shadow: 0px 0px 1px inset;
    // position: relative;
    &.resize-horizontal {
        // 窗口缩小，本分隔带的宽度会缩小，原因是 flex-shrink 默认值为 1
        flex-shrink: 0;
        width: $thickness;
        height: 100%;
        cursor: w-resize;
    }
    &.resize-vertical {
        width: 100%;
        height: $thickness;
        cursor: s-resize;
    }
    .tip {
        height: 20px;
        position: absolute;
        top: 40%;
        .line {
            width: 1px;
            height: 80px;
            margin-left: 1px;
            background-color: $resize-tip-line-color;
            float: left;
        }
    }
}

.resize-hover {
    background-color: $resize-hover-color;
}
</style>