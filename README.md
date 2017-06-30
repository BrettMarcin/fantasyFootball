# Fantasy Football
 
 # MySql
 **If you already have MySql please confirm you have version  5.7.15 or newer**
 1. Download MySql
     * Windows: https://dev.mysql.com/downloads/installer/
     * Mac: Download Homebrew:
         * Homebrew: https://brew.sh/
         * Download MySql: brew install mysql
2. Set up user and database
    ``` 
    mysql -u root
    CREATE DATABASE teams;
    CREATE USER 'new_user'@'localhost' IDENTIFIED BY 'new_password';
    GRANT ALL PRIVILEGES ON teams.* TO 'new_user'@'localhost';
    quit;
    ```
