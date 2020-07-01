package top.orange233.yizhan.common.network

class NetConfig {
    object News {
        const val BASE_URL = "https://news-at.zhihu.com/api/4/"
        const val METHOD_GET_LATEST_NEWS = "news/latest"
        const val METHOD_GET_BEFORE_NEWS = "news/before/"
        const val METHOD_GET_NEWS_BY_ID = "news/"
    }

    object Anime {
        const val BASE_URL_ANIME_LIST = "https://api.bilibili.com/pgc/season/index/"
        const val BASE_URL_ANIME_SEARCH = "https://api.pingcc.cn/"
        const val METHOD_GET_LATEST_ANIME =
            "result?season_version=-1&area=-1&is_finish=-1&copyright=-1&season_status=-1&season_month=-1&year=-1&style_id=-1&order=0&st=1&sort=0&season_type=1&pagesize=20&type=1"
        const val METHOD_SEARCH_ANIME_BY_NAME = "."
        const val METHOD_GET_EPISODE_INFO = "."
    }

    object Backend {
        const val BASE_URL = "https://yizhan.orange233.top/api/v1/"
        const val METHOD_LOGIN = "login"
        const val METHOD_REGISTER = "register"
        const val METHOD_GET_PROFILE = "profile"
        const val METHOD_CHANGE_PROFILE = "profile"
    }
}