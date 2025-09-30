CREATE TABLE IF NOT EXISTS notification_entity (
                                            id BIGSERIAL PRIMARY KEY,
                                            asunto       TEXT NOT NULL,
                                            html_body    TEXT,
                                            text_body    TEXT,
                                            email_user   TEXT,
                                            to_sms       TEXT,
                                            to_whatsapp  TEXT,
                                            status       TEXT,
                                            channel      TEXT
);