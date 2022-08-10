from apscheduler.schedulers.background import BackgroundScheduler
from pytz import timezone

from .save_to_db import save_db
from .save_to_db import save_bank_db
from .save_to_db import test_connect
def start():
    scheduler = BackgroundScheduler(timezone='Asia/Seoul')
    #scheduler.add_job(save_db, 'interval', seconds = 10)
    #scheduler.add_job(save_bank_db, 'interval', seconds = 10)
    #scheduler.add_job(save_db, trigger='cron', day_of_week='wed', hour=11, minute=15)
    #scheduler.add_job(save_db, trigger='cron', hour=17, minute=34)
    scheduler.add_job(test_connect, trigger='cron', hour=17, minute=40)
    scheduler.start()
    
    
def start_bank():
    scheduler = BackgroundScheduler(timezone='Asia/Seoul')
    scheduler.add_job(save_bank_db, trigger='cron',hour=11, minute=29)
    scheduler.start()    