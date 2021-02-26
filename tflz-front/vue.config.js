module.exports = {
    devServer: {
        port: 1234,
        proxy: {
            // detail: https://cli.vuejs.org/config/#devserver-proxy
            '/api': {
                target: `http://localhost:8080`,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    configureWebpack: {
        externals: {
            vue: 'Vue',
            'vue-router': 'VueRouter',
            vuex: 'Vuex',
            axios: 'axios',
            'element-ui': 'ELEMENT'
        }
    }
    // lintOnSave: false   // 取消 eslint 验证
};
