module.exports = {
    lintOnSave: false,
    productionSourceMap: false,
    publicPath: './',
    devServer: {
        host:'0.0.0.0',
        port: 8888,
        https:false,
        hotOnly:false,
        proxy: {
            '/campback':{
                target:'http://localhost:3000',
                pathRewrite:{
                    '^/campback':'/campback'
                }
            }
        }
    }
}