package it.weMake.covid19Companion.utils

const val ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

const val COVID_19_COMPANION_EXTERNAL_STORAGE_DIR = "/Covid-19 Companion"
const val WHO_HAND_HYGIENE_PDF_URL = "https://www.who.int/gpsc/5may/Hand_Hygiene_Why_How_and_When_Brochure.pdf"
const val WHO_HAND_HYGIENE_PDF = "Hand Hygiene Why How and When Brochure.pdf"

// Broadcast receiver Actions
const val ACTION_DOWNLOAD_STOPPED = "it.weMake.covid19Companion.action.DOWNLOAD_STOPPED"
const val ACTION_DOWNLOAD_COMPLETED = "it.weMake.covid19Companion.action.DOWNLOAD_COMPLETED"
const val ACTION_DOWNLOAD_RUNNING = "it.weMake.covid19Companion.action.DOWNLOAD_RUNNING"

const val PUBLIC_HEALTH_ON_CALL_FACEBOOK_PAGE_URL = "https://www.facebook.com/JohnsHopkinsSPH"
const val PUBLIC_HEALTH_ON_CALL_TWITTER_HANDLE_URL = "http://twitter.com/PublicHealthPod"
const val PUBLIC_HEALTH_ON_CALL_SPOTIFY_URL = "https://open.spotify.com/show/0ipz4Jv2D7utXXzaQ7wKzU"
const val PUBLIC_HEALTH_ON_CALL_APPLE_PODCASTS_URL = "https://podcasts.apple.com/us/podcast/public-health-on-call/id1501336958?ls=1"
const val PUBLIC_HEALTH_ON_CALL_GOOGLE_PODCASTS_URL = "https://podcasts.google.com/?feed=aHR0cDovL2pvaG5zaG9wa2luc3NwaC5saWJzeW4uY29tL3Jzcw%3D%3D"
const val PUBLIC_HEALTH_ON_CALL_YOUTUBE_CHANNEL_URL = "http://www.youtube.com/JohnsHopkinsSPH"

const val QUESTION_TYPE_SINGLE = "single"
const val QUESTION_TYPE_GROUP_SINGLE = "group_single"
const val QUESTION_TYPE_GROUP_MULTIPLE = "group_multiple"

const val DIAGNOSIS_LEVEL_NO_RISK = "no_risk"
const val DIAGNOSIS_LEVEL_SELF_MONITORING = "self_monitoring"
const val DIAGNOSIS_LEVEL_SELF_QUARANTINE = "quarantine"
const val DIAGNOSIS_LEVEL_ISOLATION_CALL = "isolation_call"
const val DIAGNOSIS_LEVEL_CALL_DOCTOR = "call_doctor"
const val DIAGNOSIS_LEVEL_ISOLATION_AMBULANCE = "isolation_ambulance"

const val ONE_SECOND_IN_MILLI = 1000L
const val ONE_MINUTE_IN_MILLI = 60 * ONE_SECOND_IN_MILLI

const val SORT_BY_CONFIRMED = "totalConfirmed"
const val SORT_BY_RECOVERED = "totalRecovered"
const val SORT_BY_DEATHS = "totalDeaths"

const val EXTRA_ALARM_INTERVAL = "it.weMake.covid19Companion.extra.EXTRA_ALARM_INTERVAL"

const val WASH_HANDS_PENDING_INTENT_REQUEST_CODE = 0
const val DRINK_WATER_PENDING_INTENT_REQUEST_CODE = 1
const val REMINDERS_NOTIFICATION_DEFAULT_TONE_CHANNEL_ID = "RemindersDefaultToneChannel"
const val REMINDERS_NOTIFICATION_CUSTOM_TONE_CHANNEL_ID = "RemindersCustomToneChannel"
const val DEFAULT_NOTIFICATION_CHANNEL_ID = "DefaultNotificationChannel"
const val WASH_HANDS_NOTIFICATION_ID = 101
const val DRINK_WATER_NOTIFICATION_ID = 102
const val LOCATION_WASH_HANDS_NOTIFICATION_ID = 103
const val DAILY_MOTIVATION_NOTIFICATION_ID = 104
const val APP_UPDATE_NOTIFICATION_ID = 104

const val GEOFENCE_RADIUS_IN_METERS = 100.toFloat()
const val GEOFENCE_EXPIRATION_IN_MILLISECONDS = -1
const val GEOFENCE_LOITERING_DELAY_IN_MILLISECONDS = 3 * ONE_MINUTE_IN_MILLI.toInt()

const val FCM_KEY_NOTIFICATION_TYPE = "notification_type"
const val FCM_NOTIFICATION_TYPE_DAILY_MOTIVATION = "daily_motivation"
const val FCM_NOTIFICATION_TYPE_APP_UPDATE_AVAILABLE = "app_update_available"
const val FCM_KEY_DAILY_MOTIVATION_MESSAGE = "daily_motivation_message"
const val FCM_KEY_LATEST_APP_VERSION_NAME = "latest_app_version_name"
const val FCM_KEY_LATEST_APP_VERSION_CODE = "latest_app_version_code"
const val FCM_KEY_LATEST_APP_VERSION_DETAILS = "latest_app_version_details"
const val APP_DOWNLOAD_URL = "http://richardsaseun.com/Covid_19_Companion.apk"


const val WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST = 1