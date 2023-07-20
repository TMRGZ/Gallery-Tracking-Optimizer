db.createUser({
    user: "rv_user",
    pwd: "rv_password",
    roles: [
        {
            role: "readWrite",
            db: "mongo_rv_database"
        }
    ]
});