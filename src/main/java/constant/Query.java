package constant;

public class Query {
    public static final String TV_ID_QUERY_USER = "SELECT * FROM users Where name = 'key'";
    public static final String TV_PLAY_QUERY_EVENT_TV_ACTIVE = "SELECT * FROM event_eventtv where is_active = 'key'";
    public static final String TV_PLAY_QUERY_EVENT_TV_NAME = "SELECT * FROM event_eventtv where name ='key'";
    public static final String TV_PLAY_QUERY_EVENT_VIDEO = "SELECT * FROM event_video where id = 'key'";
    public static final String TV_PLAY_QUERY_USER_EVENT_VOD = "SELECT * FROM user_eventvod where user_id='key'";
    public static final String TV_PLAY_QUERY_EVENT_VIDEO_TAG = "SELECT * FROM event_video where is_active = 'key' and tag = 'Bundesliga, '";
    public static final String TV_PLAY_QUERY_EVENT_VIDEO_LEAGUE_TAG = "SELECT * FROM event_video where is_active = 'true' and tag = 'vleague 1, '";
    public static final String TV_PLAY_QUERY_SCREEN_BLOCK = "SELECT * FROM screenblock_screenblock where name = 'key'";
    public static final String TV_PLAY_QUERY_EVENT_TV_SCREEN_BLOCK = "SELECT * FROM event_eventtv_screen_blocks where eventtv_id = 'key'";
    public static final String TV_PLAY_QUERY_LIVE_SCORE = "SELECT * FROM livescore_livescore WHERE start BETWEEN 'date 00:00:00' AND 'date 23:59:59' and is_visible = 'true' order by start";
    public static final String TV_PLAY_QUERY_LEAGUE = "SELECT * FROM league_league where id='key'";
    public static final String TV_PLAY_QUERY_CATEGORY = "SELECT * FROM categorys_categories where id = 'key'";
    public static final String TV_PLAY_QUERY_ID_CATEGORY = "SELECT * FROM categorys_categories where is_visible = 'true' order by id";
    public static final String MON_GO_DB_QUERY_DEVICE = "SELECT * FROM session_device Where user_id = 'key'";
    public static final String TV_PLAY_UPLOAD = "SELECT * FROM `tvplay-upload`.video_item where title ='key';";



    public static final String TV_PLAY_CATEGORIES_TYPE_VISIBLE = "SELECT * FROM categorys_categoriestype where is_visible = true order by id";
    public static final String TV_PLAY_CATEGORIES_TYPE = "SELECT * FROM public.categorys_categories where category_type = 'key'";
    public static final String TV_PLAY_CATEGORIES_NAME = "SELECT * FROM categorys_categoriestype where name = 'key' and is_visible ='true'";
    public static final String TV_PLAY_CATEGORIES_TYPE_ORDER_BY_NAME = "SELECT * FROM public.categorys_categories where category_type = 'key' and is_visible = 'true' order by name";
    public static final String TV_PLAY_EVENT_TV_CATEGORY = "SELECT * FROM event_eventtv where category_id ='key' AND (status = 'live' OR status = 'not_started')";
    public static final String TV_PLAY_EVENT_TV_CATEGORY_NOT_START = "SELECT * FROM event_eventtv where category_id ='key' AND status = 'not_started'";
    public static final String TV_PLAY_EVENT_TV_CATEGORY_LIVE = "SELECT * FROM event_eventtv where category_id ='key' AND status = 'live'";
    public static final String TV_PLAY_EVENT_VIDEO_CATEGORY = "SELECT * FROM public.event_video where categorys_id = 'key' and 1 = ANY (event_video.os_display) and is_active = 'true'";




}
