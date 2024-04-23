package com.elmenus.domain.drone.exception

import com.elmenus.domain.common.exception.DomainException
import com.elmenus.domain.drone.token.DroneDomainTokens

class DroneNotFoundException : DomainException(DroneDomainTokens.DRONE_NOT_FOUND)