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
        const val METHOD_GET_LATEST_ANIME =
            "result?season_version=-1&area=-1&is_finish=-1&copyright=-1&season_status=-1&season_month=-1&year=-1&style_id=-1&order=0&st=1&sort=0&season_type=1&pagesize=20&type=1"
    }
}