module.exports = {
    lintOnSave: false,
    productionSourceMap: false,
    publicPath: './',
    devServer: {
        open: true,
        host:'0.0.0.0',
        port: 8889,
        https:false,
        hotOnly:false,
        proxy: {
            '/campback':{
                // target:'http://localhost:3000',
                target: 'http://112.126.67.240:3000',
                pathRewrite:{
                    '^/campback':'/'
                }
            }
        }
    }
}