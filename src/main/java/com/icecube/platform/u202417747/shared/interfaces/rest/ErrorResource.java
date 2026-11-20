package com.icecube.platform.u202417747.shared.interfaces.rest;

/** Error JSON body used by all REST error handlers.
 * @param message localized explanation
 * @author Kevin Salazar
 */
public record ErrorResource(String message) { }
