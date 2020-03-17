//配置接口前缀
export const studentPrefix = '/campback/student';
export const memberPrefix = '/campback/member';
export const indexPrefix = '/campback/index';

//配置无需路由拦截的页面
export const canGoPath = [
    '/login',
    '/404',
    '/courses/previous',
    '/courses/now',
    '/courses/future',
    '/contact',
    '/',
]