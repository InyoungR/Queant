from django.apps import AppConfig


class QueantAppConfig(AppConfig):
    name = 'queant_app'
    from . import updater
    print("Sever running!")
    updater.start()
    updater.start_bank()