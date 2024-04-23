package com.elmenus.domain.drone.exception

import com.elmenus.domain.common.exception.DomainException
import com.elmenus.domain.drone.token.DroneDomainTokens

class InvalidDroneStateException : DomainException(DroneDomainTokens.INVALID_DRONE_STATE)
