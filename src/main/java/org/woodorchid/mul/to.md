| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [DEL key] 该命令用于在 key 存在是删除 key。 |
| 2    | [DUMP key] 序列化给定 key ，并返回被序列化的值。 |
| 3    | [EXISTS key] 检查给定 key 是否存在。 |
| 4    | [EXPIRE key] seconds 为给定 key 设置过期时间。 |
| 5    | [EXPIREAT key timestamp]。 |
| 6    | [PEXPIRE key milliseconds] 设置 key 的过期时间亿以毫秒计。 |
| 7    | [PEXPIREAT key milliseconds-timestamp] 以毫秒计 |
| 8    | [KEYS pattern]的 key 。 |
| 9    | [MOVE key db] 将当前数据库的 key 移动到给定的数据库 db 当中。 |
| 10   | [PERSIST key] 移除 key 的过期时间，key 将持久保持。 |
| 11   | [PTTL key] 以毫秒为单位返回 key 的剩余的过期时间。 |
| 12   | [TTL key]。 |
| 13   | [RANDOMKEY] 从当前数据库中随机返回一个 key 。 |
| 14   | [RENAME key newkey] 修改 key 的名称 |
| 15   | [RENAMENX key newkey] 仅当 newkey 不存在时，将 key 改名为 newkey 。 |
| 16   | [TYPE key] 返回 key 所储存的值的类型。 |
