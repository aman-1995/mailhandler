CREATE TABLE IF NOT EXISTS `lzc_mail_configurations`
(
   config_id             VARCHAR(59)
                           CHARACTER SET utf8
                           COLLATE utf8_general_ci
                           NOT NULL,
   config_name           VARCHAR(200)
                           CHARACTER SET utf8
                           COLLATE utf8_general_ci
                           NOT NULL,
   config_value          VARCHAR(500)
                           CHARACTER SET utf8
                           COLLATE utf8_general_ci
                           NOT NULL,
   config_description    VARCHAR(500)
                           CHARACTER SET utf8
                           COLLATE utf8_general_ci
                           NOT NULL,
   updated_on            TIMESTAMP(0)
                           NOT NULL
                           DEFAULT CURRENT_TIMESTAMP()
                           ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(config_id)
)
ENGINE INNODB
COLLATE 'utf8_general_ci'
ROW_FORMAT DEFAULT;

CREATE TABLE IF NOT EXISTS `lzc_mail_details`
(
   mail_id                VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   mail_to                TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   mail_from              TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   mail_cc                TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'NULL',
   mail_subject           TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   mail_body              MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   mail_sent_status       INT(2) NOT NULL DEFAULT 0,
   mail_status_message    TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'NULL',
   PRIMARY KEY(mail_id)
)
ENGINE INNODB
COLLATE 'utf8_general_ci'
ROW_FORMAT DEFAULT;

CREATE UNIQUE INDEX IF NOT EXISTS `config_name_index` ON lzc_mail_configurations(config_name);

replace into lzc_mail_configurations VALUES(UUID(), 'mail.smtp.host', 'localhost', 'Mail SMTP Host IP', NOW());
replace into lzc_mail_configurations VALUES(UUID(), 'mail.smtp.port', '25', 'Mail SMTP Port Number', NOW());
replace into lzc_mail_configurations VALUES(UUID(), 'mail.smtp.securityProtocol', 'TLS', 'Mail SMTP Security Protocol', NOW());
replace into lzc_mail_configurations VALUES(UUID(), 'mail.smtp.auth', 'false', 'Mail Authentication Enable', NOW());
replace into lzc_mail_configurations VALUES(UUID(), 'mail.smtp.user', 'username', 'Authentication Username', NOW());
replace into lzc_mail_configurations VALUES(UUID(), 'mail.smtp.password', 'password', 'Authentication Password', NOW());