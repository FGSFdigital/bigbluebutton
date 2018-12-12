package org.bigbluebutton.common2.redis

import akka.actor.ActorSystem
import akka.event.Logging

class RedisPublisher(system: ActorSystem, clientName: String) extends RedisClientProvider(system, clientName) with RedisConnectionHandler {

  val log = Logging(system, getClass)

  subscribeToEventBus(redis, log)

  val connection = redis.connectPubSub()

  redis.connect()

  def publish(channel: String, data: String) {
    val async = connection.async();
    async.publish(channel, data);
  }

}
